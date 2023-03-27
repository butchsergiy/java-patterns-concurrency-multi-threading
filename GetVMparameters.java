import java.io.File;

public class GetVMparameters {
	public static void main(String[] args) {

		//Prints operation system and environment parameters
        System.out.println(
            "OS Name: " + System.getProperty("os.name") + "\n"+
            " OS Version: " + System.getProperty("os.version") +"\n"+
            " OS Architecture: " + System.getProperty("os.arch") +"\n"+
            " Java Version: " + System.getProperty("java.version")+"\n"
        );

        //more OS parameters
        System.out.println(
            "User Home Directory: " + System.getProperty("user.home")+"\n"+
            " User Working Directory: " + System.getProperty("user.dir") +"\n"+
            " User Name: " + System.getProperty("user.name")
        );
        
        // prints CPU and RAM parameters
        System.out.println(
            "Available Processors: " + Runtime.getRuntime().availableProcessors() + "\n"+
            " Free Memory in the JVM (MB): " + Runtime.getRuntime().freeMemory()/1024/1024 + "\n"+
            " Total Memory in the JVM (MB): " + Runtime.getRuntime().totalMemory()/1024/1024
        );

        File file = new File(".");
        long freeSpace = file.getFreeSpace();
        long totalSpace = file.getTotalSpace(); 
        System.out.println("Free disk space (MB): " + freeSpace/1024/1024);
        System.out.println("Total space (MB): " + totalSpace/1024/1024);

	}
}