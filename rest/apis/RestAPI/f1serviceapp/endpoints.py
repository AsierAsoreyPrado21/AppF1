from django.http import JsonResponse
from django.db import IntegrityError
import bcrypt
import json
def health(request):
    return JsonResponse({"status": "alive"}, status=200)

##Registro del los usuarios
def register(request):
    if request.method != 'POST':
        return JsonResponse({'error': 'HTTP method not supported'}, status=405)
    body_json = json.loads(request.body)
    json_username = body_json.get('name')
    json_email = body_json.get('email')
    json_password = body_json.get('password')

    if json_username is None or json_email is None or json_password is None:
        return JsonResponse({"error": "Missing parameter in body"}, status=400)

    if not isinstance(json_username, str) or not isinstance(json_password, str) or not isinstance(json_email, str):
        return JsonResponse({"error": "Some parameters have a invalid type"}, status=400)

    numveces = json_email.count("@")
    if numveces == 0:
        return JsonResponse({"error": "Not valid email"}, status=400)

    salted_and_hashed_password = bcrypt.hashpw(json_password.encode("utf8"),bcrypt.gensalt()).decode('utf8')

    try:
        user_object = Usuarios(name=json_username,email=json_email,password=salted_and_hashed_password,token=None)
        user_object.save()
    except IntegrityError:
        return JsonResponse({"error": "An user with that email already exists"}, status=409)
    return JsonResponse({"is_created": True}, status=201)
