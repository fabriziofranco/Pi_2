from flask import Flask, request, session, Response, redirect
from database import connector
from model import entities
import json
import time
import datetime


db = connector.Manager()
engine = db.createEngine()
app = Flask(__name__)


##############################################
#                                            #
#              FAKE INFORMATION              #
#                                            #
##############################################


@app.route('/create_fake_users', methods=['GET'])
def create_test_users():
    db_session = db.getSession(engine)
    user1 = entities.User(username="Taco", email="j-quezada@utec.edu.pe", name="Javier", lastname="Quezada",
                          password="1234")

    user2 = entities.User(
        username="Arno",
        email="arnold@utec.edu.pe",
        name="Arnol",
        lastname="Martinez",
        password="1234")

    user3 = entities.User(
        username="Tin",
        email="rod@utec.edu.pe",
        name="Rodrigo",
        lastname="Arriaga",
        password="1234")

    user4 = entities.User(
        username="Negro",
        email="pelacho@utec.edu.pe",
        name="Cesar",
        lastname="Mimbela",
        password="1234")

    user5 = entities.User(
        username="Kuro",
        email="adrianalonso@utec.edu.pe",
        name="Adrian",
        lastname="Perez",
        password="1234")

    user6 = entities.User(
        username="Darklay",
        email="dark07@utec.edu.pe",
        name="Nicolas",
        lastname="Figueroa",
        password="1234")

    user7 = entities.User(
        username="Majo",
        email="marijoseutec.edu.pe",
        name="Maria",
        lastname="Vilchez",
        password="1234")

    user8 = entities.User(
        username="Tito",
        email="h-tito@utec.edu.pe",
        name="Hector",
        lastname="Pozada",
        password="1234")

    user9 = entities.User(
        username="Chispa",
        email="fer78@utec.edu.pe",
        name="Fernando",
        lastname="Castaneda",
        password="1234")

    user10 = entities.User(
        username="Papo",
        email="pap@utec.edu.pe",
        name="Pedro",
        lastname="Calderon",
        password="1234")

    user11 = entities.User(
        username="Chairi",
        email="die159@utec.edu.pe",
        name="Diego",
        lastname="Chiri",
        password="1234")

    user12 = entities.User(
        username="Drogo",
        email="richy@utec.edu.pe",
        name="Ricardo",
        lastname="Jara",
        password="1234")

    user13 = entities.User(
        username="Mafer",
        email="mllontopd@utec.edu.pe",
        name="Mafer",
        lastname="Llontop",
        password="1234")

    user14 = entities.User(
        username="kike",
        email="parades@utec.edu.pe",
        name="Enrique",
        lastname="Paredes",
        password="1234")

    user15 = entities.User(
        username="Pelado",
        email="paulmim@utec.edu.pe",
        name="Paul",
        lastname="Mimbela",
        password="1234")

    db_session.add(user1)
    db_session.add(user2)
    db_session.add(user3)
    db_session.add(user4)
    db_session.add(user5)
    db_session.add(user6)
    db_session.add(user7)
    db_session.add(user8)
    db_session.add(user9)
    db_session.add(user10)
    db_session.add(user11)
    db_session.add(user12)
    db_session.add(user13)
    db_session.add(user14)
    db_session.add(user15)
    db_session.commit()
    return "Usuarios creados!"


@app.route('/create_fake_products', methods=['GET'])
def create_test_products():
    db_session = db.getSession(engine)

    product1 = entities.Product(description="Marca Asus", name="Laptop de 32 gb de Ram "
                                                               "15 pulgadas "
                                                               "intel core i9" 
                                                               "Disco duro sólido 1TB "
                                                               "Usada 1 año", owner_id=1, category_id=1)

    product2 = entities.Product(description="Marca Zara", name="Pantalon negro talla 32 "
                                                               "Coleccion Invierno 2017 "
                                                               "Con unos pequeños rasguños"
                                                               "Usada 1 año", owner_id=1, category_id=1)

    product3 = entities.Product(description="Marca Zara", name="Zapatos negros talla 9"
                                                               "Zapatos para hombre"
                                                               "Apariencia 8/10"
                                                               "Usada 1 año", owner_id=1, category_id=1)

    product4 = entities.Product(description="Marca Emporio Armani", name="Lentes azules"
                                                                         "Con pequeños rayones"
                                                                         "Colección verano 2018"
                                                                         "Estado 7/10"
                                                                         "Usados 1 año", owner_id=1, category_id=1)

    product5 = entities.Product(description="Marca Casa Ideas", name="Conjunto de muebles"
                                                                     "Muebles de color marrón"
                                                                     "Algunos muebles presentan arañones"
                                                                     "Apariencia 7/10"
                                                                     "Usado 2 años", owner_id=1, category_id=1)
    
    product6 = entities.Product(description="Marca Nike", name="Camiseta de fútbol "
                                                               "Equipo PSG"
                                                               "Neymar 10"
                                                               "Temporada 2018-2019 "
                                                               "Usada 3 meses", owner_id=1, category_id=1)

    product7 = entities.Product(description="Marca Nike", name="Chimpunes talla 11"
                                                               "Modelo Mercurial"
                                                               "CR7"
                                                               "Estado 9/10"
                                                               "Usados 8 meses", owner_id=1, category_id=1)

    product8 = entities.Product(description="Marca Toyota", name="Corolla 2015"
                                                                 "Color negro"
                                                                 "Mecánico"
                                                                 "Radio de fábrica"
                                                                 "12 000 km", owner_id=1, category_id=1)

    product9 = entities.Product(description="Marca Ford", name="Mustang 2018"
                                                               "Color Amarillo"
                                                               "Automático"
                                                               "Estado 10/10"
                                                               "10 000 km", owner_id=1, category_id=1)

    product10 = entities.Product(description="Marca Chevrolet", name="Camaro 2018"
                                                                     "Colo Negro"
                                                                     "Automatico"
                                                                     "Estado 9/10"
                                                                     "5 000 km", owner_id=1, category_id=1)

    product11 = entities.Product(description="Marca Totto", name="Mochila roja con negro"
                                                                 "Para cuadernos"
                                                                 "Guarda laptop"
                                                                 "3 cierres"
                                                                 "Usada 2 meses", owner_id=1, category_id=1)
    
    product12 = entities.Product(description="Marca Tommy Hilfiger", name="Camisa talla M "
                                                                          "Color blanco"
                                                                          "Estado 10/10"
                                                                          "Usada 4 meses", owner_id=1, category_id=1)

    product13 = entities.Product(description="Marca Marathon", name="Camiseta Universitario de Deportes "
                                                                    "Color Crema"
                                                                    "Estado 9/10"
                                                                    "Temporada 2019"
                                                                    "Usada 9 meses", owner_id=1, category_id=1)
    
    product14 = entities.Product(description="Marca Marathon", name="Camiseta Peru "
                                                                    "Paolo Guerrero"
                                                                    "Edicion Copa America"
                                                                    "Estado 8/10"
                                                                    "Usada 1 mes", owner_id=1, category_id=1)

    product15 = entities.Product(description="Marca Nike", name="Camiseta Alinza Lima "
                                                                "Blanquiazul"
                                                                "Estado 8/10"
                                                                "Usada 5 meses", owner_id=1, category_id=1)

    product16 = entities.Product(description="Marca Puma", name="Spikes Usada talla 10.5"
                                                                "Color Amarillo"
                                                                "Velocidad"
                                                                "Estado 7/10"
                                                                "11 meses", owner_id=1, category_id=1)

    product17 = entities.Product(description="Marca Tech21", name="Case Iphone XS Max "
                                                                  "Color negro"
                                                                  "Estado 6/10"
                                                                  "Usado 1 año", owner_id=1, category_id=1)

    product18 = entities.Product(description="Marca Ray-Ban", name="Lentes de Sol "
                                                                   "Color negro"
                                                                   "Hombre"
                                                                   "Estado 7/10"
                                                                   "Usada 7 meses", owner_id=1, category_id=1)

    product19 = entities.Product(description="Marca Huawei", name="Laptop 13 pulgadas"
                                                                  "16 gb ram"
                                                                  "500 gb de almacenamiento"
                                                                  "Táctil"
                                                                  "Usada 2 meses", owner_id=1, category_id=1)

    product20 = entities.Product(description="Marca Apple", name="Iphone XS Max "
                                                                 "Negro"
                                                                 "256 gb"
                                                                 "Cero rasguños"
                                                                 "Estado 9/10"
                                                                 "Usado 10 meses", owner_id=1, category_id=1)

    product21 = entities.Product(description="Marca Apple", name="Ipad"
                                                                 "4 gb ram"
                                                                 "Color blanco"
                                                                 "Camara dañada"
                                                                 "Estado 5/10"
                                                                 "Usado 1 año", owner_id=1, category_id=1)

    product22 = entities.Product(description="Marca Samsung", name="Reloj "
                                                                   "Color negro"
                                                                   "Unisex"
                                                                   "Estado 10/10"
                                                                   "Usado 5 meses", owner_id=1, category_id=1)

    product23 = entities.Product(description="Marca Nike", name="Canilleras Futbol"
                                                                "Estado 3/10"
                                                                "Color rojo"
                                                                "Tienen Arañones"
                                                                "Usada 3 años", owner_id=1, category_id=1)

    product24 = entities.Product(description="Marca Samsung", name="Smart Tv "
                                                                   "50 pulgadas"
                                                                   "3D + lentes"
                                                                   "Estado 9/10"
                                                                   "Usada 1 año", owner_id=1, category_id=1)

    product25 = entities.Product(description="Marca Apple", name="Laptop "
                                                                 "32 gb ram"
                                                                 "15 pulgadas"
                                                                 "1 tb de almacenamiento"
                                                                 "Intel core i7"
                                                                 "Estado 8/10"
                                                                 "Usada 1 año", owner_id=1, category_id=1)

    product26 = entities.Product(description="Marca Apple", name="Airpods "
                                                                 "Primera generacion"
                                                                 "Case en perfecto estado"
                                                                 "Viene con cargador"
                                                                 "Estado 9/10"
                                                                 "Usado 6 meses", owner_id=1, category_id=1)

    product27 = entities.Product(description="Marca Apple", name="Apple Watch Series 4 "
                                                                 "Color negro"
                                                                 "44 mm"
                                                                 "Nike Sport"
                                                                 "Estado 8/10"
                                                                 "Usado 6 meses", owner_id=1, category_id=1)

    product28 = entities.Product(description="Marca Puma", name="Zapatilla talla 8"
                                                                "Mujer"
                                                                "Color blanco"
                                                                "Running"
                                                                "Estado 7/10"
                                                                "Usada 4 meses", owner_id=1, category_id=1)

    product29 = entities.Product(description="Marca Nike", name="Zapatilla talla 10"
                                                                "Hombre"
                                                                "Color Azul"
                                                                "Running"
                                                                "Estado 10/10"
                                                                "Usada 1 mes", owner_id=1, category_id=1)

    product30 = entities.Product(description="Marca Adidas", name="Zapatilla talla 9.5"
                                                                  "Hombre"
                                                                  "Color Blanco"
                                                                  "Pureboost"
                                                                  "Running"
                                                                  "Estado 10/10"
                                                                  "Usada 2 meses", owner_id=1, category_id=1)



    db_session.add(product1)
    db_session.add(product2)
    db_session.add(product3)
    db_session.add(product4)
    db_session.add(product5)
    db_session.add(product6)
    db_session.add(product7)
    db_session.add(product8)
    db_session.add(product9)
    db_session.add(product10)
    db_session.add(product11)
    db_session.add(product12)
    db_session.add(product13)
    db_session.add(product14)
    db_session.add(product15)
    db_session.add(product16)
    db_session.add(product17)
    db_session.add(product18)
    db_session.add(product19)
    db_session.add(product20)
    db_session.add(product21)
    db_session.add(product22)
    db_session.add(product23)
    db_session.add(product24)
    db_session.add(product25)
    db_session.add(product26)
    db_session.add(product27)
    db_session.add(product28)
    db_session.add(product29)
    db_session.add(product30)
    db_session.commit()
    return "Productos creados!"


##############################################
#                                            #
#                LOGIN MOBILE                #
#                                            #
##############################################

@app.route('/authenticate_mb', methods=['POST'])
def mobile_login():
    message = json.loads(request.data)
    username = message['username']
    password = message['password']
    sessiondb = db.getSession(engine)
    try:
        user = sessiondb.query(entities.User
                               ).filter(entities.User.username == username
                                        ).filter(entities.User.password == password
                                                 ).first()
        if user != None:
            return Response(json.dumps(
                {'message': "Authorized", "user_id": user.id, "username": user.username, "lastname": user.lastname,
                 "name": user.name}, cls=connector.AlchemyEncoder), status=200, mimetype='application/json')
        else:
            return Response(json.dumps({'response': False}, cls=connector.AlchemyEncoder), status=200,
                            mimetype='application/json')
    except Exception:
        message = {'message': 'Unauthorized'}
        message = json.dumps(message, cls=connector.AlchemyEncoder)
        return Response(message, status=401, mimetype='application/json')


@app.route('/user_mobile/<xid>', methods=['GET'])
def user_mobile(xid):
    sessiondb = db.getSession(engine)
    user = sessiondb.query(entities.User).filter(entities.User.id == xid).one()
    js = json.dumps({'user_id': user.id, 'username': user.username, 'name': user.name, 'lastname': user.lastname,
                     'email': user.email, 'password': user.password, 'uploads': user.uploads, 'record': user.record},
                    cls=connector.AlchemyEncoder)
    return Response(js, status=200, mimetype='application/json')


##############################################
#                                            #
#                  REGISTER                  #
#                                            #
##############################################

@app.route('/register', methods=['POST'])
def create_user():
    sessiondb = db.getSession(engine)
    c = json.loads(request.data)
    data = []
    users = sessiondb.query(entities.User).filter(entities.User.email == c['email'])
    for user in users:
        data.append(user)
    if (len(data) == 0):
        user = entities.User(
            username=c['username'],
            email=c['email'],
            name=c['name'],
            lastname=c['lastname'],
            password=c['password'])
        sessiondb.add(user)
        sessiondb.commit()
        message = {'message': 'Authorized'}
        js = json.dumps(message, cls=connector.AlchemyEncoder)
        return Response(js, status=200, mimetype='application/json')
    else:
        message = {'message': 'Unauthorized'}
        js = json.dumps(message, cls=connector.AlchemyEncoder)
        return Response(js, status=401, mimetype='application/json')


##############################################
#                                            #
#    EDIT AND DELETE ACCOUNT IN SETTINGS     #
#                                            #
##############################################

@app.route('/users', methods=['PUT'])
def update_user():
    time.sleep(1)
    sessiondb = db.getSession(engine)
    c = json.loads(request.data)
    try:
        user = sessiondb.query(entities.User).filter(entities.User.id == session['logged']).first()
        for key in c.keys():
            setattr(user, key, c[key])
        sessiondb.add(user)
        sessiondb.commit()
        message = {'message': 'Authorized'}
        return Response(message, status=200, mimetype='application/json')
    except Exception:
        message = {'message': 'Unauthorized'}
        return Response(message, status=401, mimetype='application/json')


@app.route('/users', methods=['DELETE'])
def delete_user():
    session_db = db.getSession(engine)
    try:
        users = session_db.query(entities.User).filter(entities.User.id == session['logged'])
        for user in users:
            session_db.delete(user)
        session_db.commit()
        message = {'message': 'Authorized'}
        return Response(message, status=200, mimetype='application/json')
    except Exception:
        message = {'message': 'Unauthorized'}
        return Response(message, status=401, mimetype='application/json')


##############################################
#                                            #
#                 Questions                  #
#                                            #
##############################################

@app.route('/questions', methods=['POST'])
def create_question():
    sessiondb = db.getSession(engine)
    c = json.loads(request.data)
    try:
        user = entities.Question(
            statment=c['statment'],
            answer=c['answer'],
            wrong1=c['wrong1'],
            wrong2=c['wrong2'],
            wrong3=c['wrong3'],
            category_id=c['category_id'])
        sessiondb.add(user)
        sessiondb.commit()
        message = {'message': 'Authorized'}
        return Response(message, status=200, mimetype='application/json')
    except Exception:
        message = {'message': 'Unauthorized'}
        return Response(message, status=401, mimetype='application/json')


@app.route('/questions', methods=['GET'])
def get_questions():
    session = db.getSession(engine)
    dbResponse = session.query(entities.Question)
    data = []
    for user in dbResponse:
        data.append(user)
    return Response(json.dumps(data, cls=connector.AlchemyEncoder), mimetype='application/json')


##############################################
#                                            #
#                  Get Users                 #
#                                            #
##############################################

@app.route('/users', methods=['GET'])
def get_users():
    session = db.getSession(engine)
    dbResponse = session.query(entities.User)
    data = []
    for user in dbResponse:
        data.append(user)
    return Response(json.dumps(data, cls=connector.AlchemyEncoder), mimetype='application/json')

@app.route('/products', methods = ['GET'])
def get_products():
    session = db.getSession(engine)
    dbResponse = session.query(entities.Product)
    data = []
    for p in dbResponse:
        data.append(p)
    message = {'data': data}
    return Response(json.dumps(message, cls=connector.AlchemyEncoder), mimetype='application/json')


@app.route('/product_by_id/<id>', methods = ['GET'])
def get_product_by_id(id):
    session = db.getSession(engine)
    product = session.query(entities.Product).filter(entities.Product.id == id).one()
    js = json.dumps({'name': product.name, 'description': product.description}, cls=connector.AlchemyEncoder)
    return Response(js, status=200, mimetype='application/json')





##############################################
#                                            #
#                  Testing                   #
#                                            #
##############################################


@app.route('/create_transaction', methods=['POST', 'GET'])
def create_transaction():
    transaction = entities.Transaction(
        user_from_id=1,
        user_to_id=2,
        ids_enviados=[1, 2, 4, 5, 7],
        ids_requeridos=[1, 2, 4, 5, 7]
    )
    session = db.getSession(engine)
    session.add(transaction)
    session.commit()
    return 'Created Transaction'


@app.route('/transactions', methods=['GET'])
def get_transactions():
    session = db.getSession(engine)
    dbResponse = session.query(entities.Transaction)
    data = []
    for t in dbResponse:
        data.append(t)
    return Response(json.dumps(data, cls=connector.AlchemyEncoder), mimetype='application/json')


##############################################
#                                            #
#                   Run                      #
#                                            #
##############################################

if __name__ == '__main__':
    app.secret_key = ".."
    app.run(port=8080, threaded=True, host='127.0.0.1')
