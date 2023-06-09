import json
import secrets
from django.views.decorators.csrf import csrf_exempt
import bcrypt
from django.http import JsonResponse
from django.db import IntegrityError
from.models import Usuarios
from django.views.decorators.csrf import csrf_exempt
import bcrypt
import json
def health(request):
    return JsonResponse({"status": "alive"}, status=200)

##Post Login
@csrf_exempt
def sessions(request):
    if request.method != 'POST':
        return JsonResponse({'error': 'HTTP method not supported'}, status=405)
    body_json = json.loads(request.body)
    json_email = body_json.get('email')
    json_password = body_json.get('password')
    ##validacion
    if json_email is None:
        return JsonResponse({"error": "Missing parameter"}, status=400)
    ##Consulta
    try:
        if json_email is not None:
            db_user = Usuarios.objects.get(email=json_email)
    except Usuarios.DoesNotExist:
        return JsonResponse({"error": "This user does not exist"}, status=404)
    if bcrypt.checkpw(json_password.encode('utf8'), db_user.password.encode('utf8')):
        random_token = secrets.token_hex(100)
        db_user.token = random_token
        db_user.save()
        return JsonResponse({"email": json_email, "token": random_token}, status=201)
    else:
        return JsonResponse({"error": "This password is incorrect"}, status=400)


##Registro del los usuarios
@csrf_exempt
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
