import json
import secrets
from django.views.decorators.csrf import csrf_exempt
import bcrypt
from django.http import JsonResponse

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



