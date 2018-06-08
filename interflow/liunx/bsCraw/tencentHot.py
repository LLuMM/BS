#*_coding:utf-8_*_

from lxml import etree
import requests
import json
import random
import urllib2
import MySQLdb
import uuid


url = "http://news.qq.com/"
headers = {"User-Agent":"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT6.1; Trident/5.0"}
proxy_list = [{"http":"183.159.85.65:18118"},{"http":"223.241.117.35:8010"},{"http":"39.104.120.252:8888"},{"http":"223.243.208.138:61588"},{"http":"60.177.229.183:18118"},{"http":"183.159.90.18:18118"}]


def loadPage(html):
    source = etree.HTML(html)
    nodes = source.xpath('//div[@class="head"]/div/div[@class="Q-tpWrap"]')
    print nodes
    '''
    #items = []
    for node in nodes:
	item = {}
	item['image'] = node.xpath('./a/img/@src')[0]
	
	item['title'] = node.xpath('/div/em/a/text()')
	item['href'] = node.xpath('/a/@href')
	#print node.xpath('//ul[@class="entry-list"]//li//a[@class="title"]/')[0].text.encode('utf-8')

	items.append(item)
	writePage(items)
   	'''
def loadContent(item):
    response = requests.get(item['content'], headers = headers)
    html = response.text
    nodes = etree.HTML(html)
    for node in nodes:
	node.xpath('//div[@class="htmledit_views"]/p/span/')
	print node
	break

def writePage(items):
    db = MySQLdb.connect("192.168.80.1", "root", "rootlm", "interflow", charset='utf8')
    cur = db.cursor()
    #sql = "delete from new"
    cur.execute(sql)
    db.commit()
    for item in items:
        if len(item) > 0:
            content = str(loadContent(item))
            nid = uuid.uuid1()
            n_id = uuid.uuid4()
            sqlc = "insert into new(nid, title, n_id, type) values('%s', '%s', '%s', '%d')"%(nid, item['title'],n_id, 2)
            #sqla = "insert into nsnew(nsid, title, content) values('%s', '%s', '%s')"%(n_id, item['title'], content)
            try:
                cur.execute(sqlc)
                #cur.execute(sqla)
                print "success"
                db.commit()
            except:
                print "error"
                db.rollback()

    db.close()


def main():
    proxy = random.choice(proxy_list)
    httpproxy_handler = urllib2.ProxyHandler(proxy)
    opener = urllib2.build_opener(httpproxy_handler)
    request = urllib2.Request(url, headers = headers)
    response = opener.open(request)
    html = response.read()
    loadPage(html)

if __name__ == "__main__":
    main()
