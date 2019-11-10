from sqlalchemy import Column, Integer, String, Sequence, DateTime, ForeignKey, PickleType, ARRAY, func
from sqlalchemy.orm import relationship
from sqlalchemy.ext.mutable import Mutable
from database import connector
import datetime


class User(connector.Manager.Base):
    # Usuarios existentes
    __tablename__ = 'users'
    id = Column(Integer, Sequence('user_id_seq'), primary_key=True)
    email = Column(String(200))
    name = Column(String(150))
    lastname = Column(String(150))
    password = Column(String(50))
    username = Column(String(50))


class Category(connector.Manager.Base):
    # Tipos de producto admitibles
    __tablename__ = 'categories'
    id = Column(Integer, Sequence('category_id_seq'), primary_key=True)
    name = Column(String(50))


class Product(connector.Manager.Base):
    __tablename__ = 'products'
    id = Column(Integer, Sequence('product_id_seq'), primary_key=True)
    name = Column(String(300))
    description = Column(String(3000))

    category_id = Column(Integer, ForeignKey('categories.id'))
    category = relationship(Category, foreign_keys=[category_id])

    owner_id = Column(Integer, ForeignKey('users.id'))
    owner = relationship(User, foreign_keys=[owner_id])

    url = Column(String(5000), default="ZZZ")


class Transaction(connector.Manager.Base):
    __tablename__ = 'transactions'
    id = Column(Integer, Sequence('transaction_id_seq'), primary_key=True)
    user_from_id = Column(Integer, ForeignKey('users.id'))
    user_to_id = Column(Integer, ForeignKey('users.id'))
    user_from = relationship(User, foreign_keys=[user_from_id])
    user_to = relationship(User, foreign_keys=[user_to_id])
    ids_enviados = Column(PickleType)
    id_requeridos = Column(Integer)
    name_requerido = Column(String(5000))


