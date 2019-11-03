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
    product1 = entities.Product(description="Marca Asus", name="Laptop Usada 1 año", owner_id=1, category_id=1)

    product2 = entities.Product(description="Marca Asus", name="Libro Usada 1 año", owner_id=1, category_id=1)

    product3 = entities.Product(description="Marca Asus", name="Zapatos Usada 1 año", owner_id=1, category_id=1)

    product4 = entities.Product(description="Marca Asus", name="Lentes Usada 1 año", owner_id=1, category_id=1)

    product5 = entities.Product(description="Marca Asus", name="Colonia Usada 1 año", owner_id=1, category_id=1)
    
    product6 =entities.Product(description="Marca Asus", name="Celular Usada 1 año", owner_id=1, category_id=1)

    product7 = entities.Product(description="Marca Asus", name="Adorno Usada 1 año", owner_id=1, category_id=1)

    product8 = entities.Product(description="Marca Asus", name="Stewart Usada 1 año", owner_id=1, category_id=1)

    product9 = entities.Product(description="Marca Asus", name="Bolsa Usada 1 año", owner_id=1, category_id=1)

    product10 = entities.Product(description="Marca Asus", name="Linterna Usada 1 año", owner_id=1, category_id=1)

    product11 = entities.Product(description="Marca Asus", name="Teclado Usada 1 año", owner_id=1, category_id=1)
    
    product12 = entities.Product(description="Marca Asus", name="Piano Usada 1 año", owner_id=1, category_id=1)

    product13 = entities.Product(description="Marca Asus", name="Cuadro Usada 1 año", owner_id=1, category_id=1)
    
    product14 = entities.Product(description="Marca Asus", name="Tv Usada 1 año", owner_id=1, category_id=1)

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

@app.route('/products', methods=['GET'])
def get_products():
    session = db.getSession(engine)
    dbResponse = session.query(entities.Product)
    data = []
    for user in dbResponse:
        data.append(user)
    return Response(json.dumps(data, cls=connector.AlchemyEncoder), mimetype='application/json')


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
