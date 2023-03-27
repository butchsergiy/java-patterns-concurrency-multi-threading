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
            " Free Memory: " + Runtime.getRuntime().freeMemory() + "\n"+
            " Total Memory: " + Runtime.getRuntime().totalMemory()
        );

        
	}
}