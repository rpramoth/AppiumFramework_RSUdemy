import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime r =Runtime.getRuntime();
		r.exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\EmulatorLaunch.bat");
		Thread.sleep(15000);
	}
	
}
