#*_coding:utf-8_*_
import urllib2
import jsonpath
import json
import chardet
import random
import urllib2
import MySQLdb
import uuid
import time

def gethtml():
    url = 'https://www.hao123.com/feedData/exch?&type=rec'
    request =urllib2.Request(url)
    response = urllib2.urlopen(request)
    html = response.read()
    jsonobj = json.loads(html)
    titlelist = jsonpath.jsonpath(jsonobj,'$..title')
    imgurllist = jsonpath.jsonpath(jsonobj,'$..img0')
    urllist = jsonpath.jsonpath(jsonobj,'$..url')
    sourcelist = jsonpath.jsonpath(jsonobj,'$..source')
    timelist = jsonpath.jsonpath(jsonobj,'$..intime')
    writePage(titlelist, urllist, sourcelist, imgurllist,timelist)


def writePage(titlelist, urllist, sourcelist, imgurllist, timelist):
    db = MySQLdb.connect("192.168.80.1", "root", "rootlm", "interflow", charset='utf8')
    cur = db.cursor()
    sql1 = "delete from news where type = 0"
    try:
        cur.execute(sql1)
        db.commit()
    except:
        print "delete error"

    if len(titlelist) > 0:
        for i in range(20):
            time_local = time.localtime(timelist[i])
            dt = time.strftime("%Y-%m-%d %H:%M:%S",time_local)	    
            nid = uuid.uuid1()
	    num = random.randint(0,1000)	
            sqlc = "insert into news(nid, title, n_id, type, url, source, imgurl, date, readnum) values('%s', '%s', '%s', '%d', '%s', '%s', '%s','%s', '%d')"%(nid, titlelist[i],nid, 0, urllist[i], sourcelist[i], imgurllist[i], dt, num)
            try:
                cur.execute(sqlc)
                print "success"
                db.commit()
            except:
                print "error"
                db.rollback()

    db.close()

def main():
    gethtml()

if __name__ == "__main__":
    main()
