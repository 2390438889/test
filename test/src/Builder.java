
/**
 建造者模式
 */
public class Builder{
    public static void main(String[] args){
        ClassLoader classLoader=Builder.class.getClassLoader();
        System.out.println(classLoader.getResource("test/src/Builder.java"));
    }
}