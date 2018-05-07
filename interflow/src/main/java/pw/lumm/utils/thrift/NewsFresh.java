package pw.lumm.utils.thrift;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class NewsFresh {
    public static void main(String[] args) {
        getNews();
    }
    public static void getNews() {
        //配置服务端的请求信息
        TTransport transport = new TSocket("192.168.202.129", 9090);
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            getHotNews.Client client = new getHotNews.Client(protocol);
            String news1 = client.getNews1();
           /* String news2 = client.getNews2();*/
            if ("true".equals(news1)/*&&"true".equals(news2)*/){
                System.out.println("成功");
            }

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}