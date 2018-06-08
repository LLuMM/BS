#_*_coding:utf-8_*_

from lxml import etree
import requests
import json
import random
import urllib2
import MySQLdb
import uuid
import time

url = "https://juejin.im/welcome/backend"
headers = {"User-Agent":"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT6.1; Trident/5.0"}
proxy_list = [{"http":"183.159.85.65:18118"},{"http":"223.241.117.35:8010"},{"http":"39.104.120.252:8888"},{"http":"223.243.208.138:61588"},{"http":"60.177.229.183:18118"},{"http":"183.159.90.18:18118"}]

def loadPage(html):
   
    source = etree.HTML(html)
    nodes = source.xpath('//ul[@class="entry-list"]//li//a[@class="title"]')
    items = []
    url1 = 'https://juejin.im'
    for node in nodes:
	item = {}
	href = node.xpath('./@href')[0]
	item['href'] = url1 + href 
	item['title'] = node.xpath('.')[0].text.encode('utf-8')
	items.append(item)	
    writePage(items)
   
def loadContent(item):
    response = requests.get(item['href'], headers = headers)
    html = response.text
    nodes = etree.HTML(html)
    if len(nodes.xpath('//div[@itemprop="articleBody"]'))> 0:
        content = nodes.xpath('//div[@itemprop="articleBody"]')[0]
        contents = content.xpath("string(.)").encode("utf-8")
        return contents
    

def writePage(items):
    db = MySQLdb.connect("192.168.80.1", "root", "rootlm", "interflow", charset='utf8')
    cur = db.cursor()
    sql1 = "delete from news where type = 1"
    sql2 = "delete from nsnews"
    try:
        cur.execute(sql1)
        cur.execute(sql2)
        db.commit()
    except:
	print "delete error"
    times = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

    for item in items:
        if len(item) > 0:
	    content = str(loadContent(item))
	    nid = uuid.uuid1()
            n_id = uuid.uuid4()
	    num = random.randint(0,1000)
	    sqlc = "insert into news(nid, title, n_id, type, date, source, url,readnum) values('%s', '%s', '%s', '%d', '%s', '%s', '%s', '%d')"%(nid, item['title'],n_id, 1, times, '掘金', item['href'], num)
	    sqla = "insert into nsnews(nsid, title, content,source,nid) values('%s', '%s', '%s', '%s', '%s')"%(n_id, item['title'], content, '掘金',nid)
	    try:
	        cur.execute(sqlc)
	        cur.execute(sqla)
	        print "success"
	        db.commit()
            except Exception, e:
		#print "error:" + str(e).encode('gbk')
                db.rollback()
	
    db.close()

def mainfun():
   
    proxy = random.choice(proxy_list)
    httpproxy_handler = urllib2.ProxyHandler(proxy)
    opener = urllib2.build_opener(httpproxy_handler)

    request = urllib2.Request(url, headers = headers)
    response = opener.open(request)
    html = response.read()
    loadPage(html)
def main():
    mainfun() 


if __name__ == "__main__":
    main()
