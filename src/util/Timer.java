package util;

public class Timer {

	private  int frame;
	private  int minute;
	
	public int getMinute() {
		return minute;
	}

	private  int sec;
	private boolean ready;
	
	public Timer(){
		this.frame = 0;
		this.minute = 0;
		this.sec = 0;
		this.ready = false;
	}
	
	public Timer(int minutes){
		this.frame = 0;
		this.sec = 0;
		this.ready = false;
		this.minute = minutes;
	}
	
	public String updateTime(){
		this.frame = (this.frame+1)%80;
		if(this.frame == 0){
			this.sec = (this.sec+1)%60;
			this.ready = true;
		}
		if(this.sec == 0 && this.ready){
			this.minute++; 
			this.ready = false;
		}
		
		return String.valueOf(this.minute)+" : "+String.valueOf(this.sec);
	}
}
