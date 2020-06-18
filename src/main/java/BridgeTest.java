import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class BridgeTest {
	public static void main2(String[] args) {
		try {
			URL url = new URL("https://dds.bridgewebs.com/bsol2/ddummy.htm?file=https://areaprivata.bboitalia.it/web/upload/tornei-online/5695-1590343636.5782.lin");
			InputStream is;
			is = (InputStream) url.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void  main(String[] args) throws IOException {

		Document doc = renderPage("https://dds.bridgewebs.com/bsol2/ddummy.htm?file=https://areaprivata.bboitalia.it/web/upload/tornei-online/5695-1590343636.5782.lin");
		System.out.println(doc.title());
		Elements newsHeadlines = doc.select("makeableContracts");
		for (Element headline : newsHeadlines) {
			System.out.println(
					headline.attr("title"));
			System.out.println(
		headline.absUrl("href"));

		}

		//System.out.println(doc.body().toString());

	}




	public static Document renderPage(String filePath) {

		System.setProperty("phantomjs.binary.path", "/Users/IG01910/Documents/workspace-test/phantomjs-2.1.1-macosx/bin/phantomjs"); // path to bin file. NOTE: platform dependent
		PhantomJSDriver ghostDriver = new PhantomJSDriver();

		try {
			ghostDriver.get(filePath);
			System.out.println("te table ");
			Thread.sleep(5000);

			System.out.println(ghostDriver.findElement(By.id("makeableContracts")).getText());
			return Jsoup.parse(ghostDriver.getPageSource());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			ghostDriver.quit();
		}
		return null;
	}
}
