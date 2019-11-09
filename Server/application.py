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
    user1 = entities.User(username="Taco", email="j-quezada@utec.edu.pe\n", name="Javier", lastname="Quezada",
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

    # Tecnologia", "Hogar", "Ropa", "Libros", "Coleccionables"

    product1 = entities.Product(description="Marca Asus\n"
                                            "Laptop de 32 gb de Ram \n"
                                            "15 pulgadas\n "
                                            "intel core i9\n"
                                            "Disco duro solido 1TB \n"
                                            "Usada 1 año\n", name="Laptop", owner_id=1, category_id=1)
    product2 = entities.Product(description="Marca Zara\n"
                                            "negro talla 32\n "
                                            "Coleccion Invierno 2017\n "
                                            "Con unos pequeños rasguños\n"
                                            "Usada 1 año\n", name="Pantalon", owner_id=1, category_id=3)
    product3 = entities.Product(description="Marca Zara\n"
                                            "negros talla 9\n"
                                            "Zapatos para hombre\n"
                                            "Apariencia 8/10\n"
                                            "Usada 1 año\n", name="Zapatos", owner_id=1, category_id=3)
    product4 = entities.Product(description="Marca Emporio Armani\n"
                                            "azules\n"
                                            "Con pequeños rayones\n"
                                            "Colección verano 2018\n"
                                            "Estado 7/10\n"
                                            "Usados 1 año\n", name="Lentes", owner_id=1, category_id=3)
    product5 = entities.Product(description="Marca Casa Ideas\n"
                                            "Muebles de color marrón\n"
                                            "Algunos muebles presentan arañones\n"
                                            "Apariencia 7/10\n"
                                            "Usado 2 años\n", name="Conjunto de muebles", owner_id=1, category_id=2)
    product6 = entities.Product(description="Marca Nike\n"
                                            "Equipo PSG\n"
                                            "Neymar 10\n"
                                            "Temporada 2018-2019\n "
                                            "Usada 3 meses\n", name="Camiseta de futbol", owner_id=1, category_id=3)
    product7 = entities.Product(description="Marca Nike\n"
                                            "talla 11\n"
                                            "Modelo Mercurial\n"
                                            "CR7\n"
                                            "Estado 9/10\n"
                                            "Usados 8 meses\n", name="Chimpunes", owner_id=1, category_id=3)
    product8 = entities.Product(description="Marca Toyota\n"
                                            "Color negro\n"
                                            "Mecanico\n"
                                            "Radio de fñbrica\n"
                                            "12 000 km\n", name="Corolla 2015", owner_id=1, category_id=5)
    product9 = entities.Product(description="Marca Ford\n"
                                            "Color Amarillo\n"
                                            "Automñtico\n"
                                            "Estado 10/10\n"
                                            "10 000 km\n", name="Mustang 2018", owner_id=1, category_id=5)
    product10 = entities.Product(description="Marca Chevrolet\n"
                                             "Colo Negro\n"
                                             "Automatico\n"
                                             "Estado 9/10\n"
                                             "5 000 km\n", name="Camaro 2018", owner_id=1, category_id=5)
    product11 = entities.Product(description="Marca Totto\n"
                                             "Roja con negro\n"
                                             "Para cuadernos\n"
                                             "Guarda laptop\n"
                                             "3 cierres\n"
                                             "Usada 2 meses\n", name="Mochila", owner_id=1, category_id=3)
    product12 = entities.Product(description="Marca Tommy Hilfiger\n"
                                             "talla M \n"
                                             "Color blanco\n"
                                             "Estado 10/10\n"
                                             "Usada 4 meses\n", name="Camisa", owner_id=1, category_id=3)
    product13 = entities.Product(description="Marca Marathon\n"
                                             "Color Crema\n"
                                             "Estado 9/10\n"
                                             "Temporada 2019\n"
                                             "Usada 9 meses\n", name="Camiseta Universitario de Deportes", owner_id=1,
                                 category_id=3)
    product14 = entities.Product(description="Marca Marathon\n"
                                             "Paolo Guerrero\n"
                                             "Edicion Copa America\n"
                                             "Estado 8/10\n"
                                             "Usada 1 mes\n", name="Camiseta Peru", owner_id=1, category_id=3)
    product15 = entities.Product(description="Marca Nike\n"
                                             "Blanquiazul\n"
                                             "Estado 8/10\n"
                                             "Usada 5 meses\n", name="Camiseta Alinza Lima", owner_id=1, category_id=3)
    product16 = entities.Product(description="Marca Puma\n"
                                             "talla 10.5\n"
                                             "Color Amarillo\n"
                                             "Velocidad\n"
                                             "Estado 7/10\n"
                                             "Usada 11 meses\n", name="Spikes Atletismo", owner_id=1, category_id=3)
    product17 = entities.Product(description="Marca Tech21\n"
                                             "Color negro\n"
                                             "Estado 6/10\n"
                                             "Usado 1 año\n", name="Case Iphone XS Max", owner_id=1, category_id=1)
    product18 = entities.Product(description="Marca Ray-Ban\n"
                                             "Color negro\n"
                                             "Hombre\n"
                                             "Estado 7/10\n"
                                             "Usada 7 meses\n", name="Lentes de Sol", owner_id=1, category_id=3)
    product19 = entities.Product(description="Marca Huawei\n"
                                             "13 pulgadas\n"
                                             "16 gb ram\n"
                                             "500 gb de almacenamiento\n"
                                             "Tactil\n"
                                             "Usada 2 meses\n", name="Laptop", owner_id=1, category_id=1)
    product20 = entities.Product(description="Marca Apple\n"
                                             "Negro\n"
                                             "256 gb\n"
                                             "Cero rasguños\n"
                                             "Estado 9/10\n"
                                             "Usado 10 meses\n", name="Iphone XS Max ", owner_id=1, category_id=1)
    product21 = entities.Product(description="Marca Apple\n"
                                             "4 gb ram\n"
                                             "Color blanco\n"
                                             "Camara dañada\n"
                                             "Estado 5/10\n"
                                             "Usado 1 año\n", name="Ipad", owner_id=1, category_id=1)
    product22 = entities.Product(description="Marca Samsung\n"
                                             "Color negro\n"
                                             "Unisex\n"
                                             "Estado 10/10\n"
                                             "Usado 5 meses\n", name="Reloj", owner_id=1, category_id=1)
    product23 = entities.Product(description="Marca Nike\n"
                                             "Estado 3/10\n"
                                             "Color rojo\n"
                                             "Tienen Arañones\n"
                                             "Usada 3 años\n", name="Canilleras Futbol", owner_id=1, category_id=3)
    product24 = entities.Product(description="Marca Samsung\n"
                                             "50 pulgadas\n"
                                             "3D + lentes\n"
                                             "Estado 9/10\n"
                                             "Usada 1 año\n", name="Smart Tv", owner_id=1, category_id=1)
    product25 = entities.Product(description="Marca Apple\n"
                                             "32 gb ram\n"
                                             "15 pulgadas\n"
                                             "1 tb de almacenamiento\n"
                                             "Intel core i7\n"
                                             "Estado 8/10\n"
                                             "Usada 1 año\n", name="Laptop", owner_id=1, category_id=1)
    product26 = entities.Product(description="Marca Apple\n"
                                             "Primera generacion\n"
                                             "Case en perfecto estado\n"
                                             "Viene con cargador\n"
                                             "Estado 9/10\n"
                                             "Usado 6 meses\n", name="Airpods", owner_id=1, category_id=1)
    product27 = entities.Product(description="Marca Apple\n"
                                             "Primera generacion\n"
                                             "Case en perfecto estado\n"
                                             "Viene con cargador\n"
                                             "Estado 9/10\n"
                                             "Usado 6 meses\n", name="Apple Watch Series 4", owner_id=1, category_id=1)
    product28 = entities.Product(description="Marca Puma\n"
                                             "Talla 8\n"
                                             "Mujer\n"
                                             "Color blanco\n"
                                             "Running\n"
                                             "Estado 7/10\n"
                                             "Usada 4 meses\n", name="Zapatilla", owner_id=1, category_id=3)
    product29 = entities.Product(description="Marca Nike\n"
                                             "Talla 10\n"
                                             "Hombre\n"
                                             "Color Azul\n"
                                             "Running\n"
                                             "Estado 10/10\n"
                                             "Usada 1 mes\n", name="Zapatilla", owner_id=1, category_id=3)
    product30 = entities.Product(description="Marca Adidas\n"
                                             " talla 9.5\n"
                                             "Hombre\n"
                                             "Color Blanco\n"
                                             "Pureboost\n"
                                             "Running\n"
                                             "Estado 10/10\n"
                                             "Usada 2 meses\n", name="Zapatilla", owner_id=1, category_id=3)

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



@app.route('/createProduct', methods=['POST'])
def create_product():
    sessiondb = db.getSession(engine)
    c = json.loads(request.data)
    data = []

    product = entities.Product(
        name=c['name'],
        url=c['url'],
        description=c['description'],
        category_id=c['category_id'],
        owner_id=c['owner_id']
    )
    sessiondb.add(product)
    sessiondb.commit()
    message = {'message': 'Authorized'}
    js = json.dumps(message, cls=connector.AlchemyEncoder)
    return Response(js, status=200, mimetype='application/json')


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


@app.route('/products', methods=['GET'])
def get_products():
    session = db.getSession(engine)
    dbResponse = session.query(entities.Product)
    data = []
    for p in dbResponse:
        data.append(p)
    message = {'data': data}
    return Response(json.dumps(message, cls=connector.AlchemyEncoder), mimetype='application/json')


@app.route('/product_by_id/<id>', methods=['GET'])
def get_product_by_id(id):
    session = db.getSession(engine)
    product = session.query(entities.Product).filter(entities.Product.id == id).one()
    js = json.dumps({'name': product.name, 'description': product.description, 'url':product.url}, cls=connector.AlchemyEncoder)
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
