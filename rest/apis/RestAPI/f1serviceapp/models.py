from django.db import models
# Create your models here.
class Usuarios(models.Model):
    name = models.CharField(primary_key=True, max_length=200)
    email = models.CharField(unique=True, max_length=200)
    password = models.CharField(max_length=200)
    token = models.CharField(blank=True, null=True, max_length=200)


class Meta:
    managed = False
    db_table = 'USUARIOS'
