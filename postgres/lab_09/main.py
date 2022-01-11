import time
import uuid

from peewee import *
import redis
import matplotlib.pyplot as plt
import os
TIME = 9

r = redis.Redis(host='127.0.0.1', port=6379)
cached_key = 'topp'

db = PostgresqlDatabase(
    database='postgres',
    user='postgres',
    password='postgres',
    port='5433',
    autocommit=True
)


def top_to_str(res):
    top = []
    for r in res:
        s = str(r[0]) + ' ' + str(r[1]) + ' ' + str(r[2])
        top.append(s)
    return '\n'.join(top)


def db_request():
    res = db.execute_sql(f"""
        select owner_name, age, income
        from CompanyOwner join balance on CompanyOwner.balance_id = balance.id
        order by income desc
        limit 10;
    """)
    s = top_to_str(res)
    return s


def redis_request():
    value = r.get(cached_key)
    if value is None:
        value = db_request()
        r.set(cached_key, value)
        r.expire(cached_key, 9)
    else:
        value = value.decode()
    return value


def insert():
    db.execute_sql(f'''insert into balance (free_cash, stocks_amount, turnover, total_assets, income) 
    values (330, 200, 100, 23, 10.5);
    ''')


def update():
    db.execute_sql(
        f'''update balance set free_cash = 30 where free_cash = 330; ''')


def delete():
    db.execute_sql(
        f'''delete from balance where free_cash = 330 OR free_cash = 30; ''')




def measure(f):
    t1 = time.time()
    f()
    t2 = time.time()
    return t2 - t1




if __name__ == '__main__':
    time_cached = []
    time_uncached = []

    iterations = 6

    for i in range(iterations):
             
        time_cached.append(measure(redis_request))
        time_uncached.append(measure(db_request))
        time.sleep(5)

    
    x = [t * 5 for t in range(iterations)]
    plt.plot(x, time_cached, label='Request to Redis')
    plt.plot(x, time_uncached, label='Request to Postgres')
    plt.legend(loc='upper right')
    plt.savefig('graph_req.png')
    plt.clf()

    time_cached.clear()
    time_uncached.clear()


    
    for i in range(iterations):  
        if (i % 2):
            insert()

        time_cached.append(measure(redis_request))
        time_uncached.append(measure(db_request))
        time.sleep(5)

    x = [t * 5 for t in range(iterations)]
    plt.plot(x, time_cached, label='Request insert to Redis')
    plt.plot(x, time_uncached, label='Request insert to Postgres')
    plt.legend(loc='upper right')
    plt.savefig('graph_add.png')
    plt.clf()

    time_cached.clear()
    time_uncached.clear()


    
    for i in range(iterations):  
        if (i % 2):
            update()

        time_cached.append(measure(redis_request))
        time_uncached.append(measure(db_request))
        time.sleep(5)

    x = [t * 5 for t in range(iterations)]
    plt.plot(x, time_cached, label='Request update to Redis')
    plt.plot(x, time_uncached, label='Request update to Postgres')
    plt.legend(loc='upper right')
    plt.savefig('graph_upd.png')
    plt.clf()

    time_cached.clear()
    time_uncached.clear()



    for i in range(iterations):  
        if (i % 2):
            delete()

        time_cached.append(measure(redis_request))
        time_uncached.append(measure(db_request))
        time.sleep(5)

    x = [t * 5 for t in range(iterations)]
    plt.plot(x, time_cached, label='Request delete to Redis')
    plt.plot(x, time_uncached, label='Request delete to Postgres')
    plt.legend(loc='upper right')
    plt.savefig('graph_del.png')
    plt.clf()
        
