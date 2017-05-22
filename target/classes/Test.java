import java.net.URL;

public class Test {

	public void loadImage() {
		URL url=getClass().getResource("images/burger.jpg");
		System.out.println("Image URL : "+url);
	}

	public static void main(String ...args) {
		Test t1=new Test();
		t1.loadImage();
	}
}	
