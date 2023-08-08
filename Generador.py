import random

def generar_palabra(longitud):
    consonantes = 'bcdfghjklmnpqrstvwxyz'
    vocales = 'aeiou'
    palabra = ''
    for i in range(longitud):
        if i % 2 == 0:
            palabra += random.choice(consonantes)
        else:
            palabra += random.choice(vocales)
    return palabra

def generar_palabras(cantidad, longitud):
    palabras = ""
    for i in range(cantidad):
        palabras += generar_palabra(longitud) + "\n"
        
    with open("palabras.txt", "w") as archivo:
        archivo.write(palabras)
        

#a = input("Ingrese la cantidad de palabras a generar: ")
a= 100000
generar_palabras(int(a), 6)
    
    
