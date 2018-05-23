package pw.lumm.utils;

public class test {
    public static void main(String[] args) {
        String a = "http://192.168.202.200/group1/M00/00/00/wKjKyFr_8OOAXlxgABA68m4Nglc261.pdf";
        int i = a.indexOf("/",21);
        int j = a.lastIndexOf(".");
        String st = a.substring(i+1,i+7);
        String st1 = a.substring(i+7);
        System.out.println(st);
        System.out.println(st1);
        System.out.println(a.substring(j));
    }
}
