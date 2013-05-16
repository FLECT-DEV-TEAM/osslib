package jobs;

import play.jobs.Job;
import play.jobs.Every;
import play.libs.WS;
import play.Play;

@Every("5min")
public class PollingJob extends Job {
	
	public void doJob() {
		String url = "http://oss.flect.co.jp/public/ping.txt";
		WS.url(url).get();
    }
}
