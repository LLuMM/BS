#_*_coding:utf-8_*_
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
#根据实际的包结构去引入
from GetNew import getHotNews
import News
import hotNew

#test8.thrift的具体实现
class TestServiceHandler:
    def __init__(self):
        self.log = {}

    def getNews1(self):
	News.main()
        return "true"

    def getNews2(self):
        hotNew.main()
        return "true"


if __name__ == '__main__':
    handler = TestServiceHandler()
    processor = getHotNews.Processor(handler)
    transport = TSocket.TServerSocket(host='192.168.202.129',port=9090)
    tfactory = TTransport.TBufferedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print 'crawler server:ready to start'
    server.serve()    




                   
