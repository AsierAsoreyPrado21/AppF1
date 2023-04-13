import json
import secrets
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

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
    json_nick = body_json.get('nick')
    json_email = body_json.get('email')
    json_password = body_json.get('password')
    ##validacion
    if json_nick is None and json_email is None:
        return JsonResponse({"error": "Missing parameter"}, status=400)
    ##Consulta
    try:
        if json_email is None:
            db_user = Usuarios.objects.get(nick=json_nick)
        else:
            db_user = Usuarios.objects.get(email=json_email)
    except Usuarios.DoesNotExist:
        return JsonResponse({"error": "This user does not exist"}, status=404)
    if bcrypt.checkpw(json_password.encode('utf8'), db_user.password.encode('utf8')):
        random_token = secrets.token_hex(100)
        db_user.token = random_token
        #session = Usuarios(email=db_user, token=random_token, nick=json_nick)
        db_user.save()
        return JsonResponse({"nick": json_nick, "token": random_token}, status=201)
    else:
        return JsonResponse({"error": "This password is incorrect"}, status=400)


##Recuperar contraseña
@csrf_exempt

def restore(request):
    if request.method != 'POST':
        return JsonResponse({'error': 'Método HTPP no soportado'}, status=405)
    body_json = json.loads(request.body)

    try:
        json_email = body_json['email']
    except KeyError:
        return JsonResponse({"error": "Campo vacio"}, status=400)
    #comentar este try para comprobar
    try:
        db_user = Usuarios.objects.get(email=json_email)
    except Usuarios.DoesNotExist:
        return JsonResponse({"error": "Usuario no encontrado"}, status=404)

    #password_token = secrets.token_hex(10)
    #db_user.password_token = password_token




    # create message object instance  #{password_token}#
    msg = MIMEMultipart("alternative")
    message = f"""
        <html>
        <body>
            <p align= center> ¡Hola, {db_user.name}<i></i>! </p> <br> 
            <p align= center> Has solicitado restablecer tu contraseña:  </p>
            <p align= center> Not Found =) </b> </p> 
        </body>
        <html>
         """

    # set up the parameters of the message
    password_mail = "Asier21197"
    msg['From'] = "aasoreyp@fpcoruna.afundacion.org"
    msg['To'] = db_user.email #usar correo de arriba en vez de esto para comprobar que funciona
    msg['Subject'] = "Restablecer la contraseña"


    # add in the message body
    msg.attach(MIMEText(message, 'html'))

    # create server
    server = smtplib.SMTP('smtp.gmail.com: 587')
    server.starttls()

    # Login Credentials for sending the mail
    server.login(msg['From'], password_mail)

    # send the message via the server.
    server.sendmail(msg['From'], msg['To'], msg.as_string())
    server.quit()
    return JsonResponse({"successful": "OK"}, status=201)
