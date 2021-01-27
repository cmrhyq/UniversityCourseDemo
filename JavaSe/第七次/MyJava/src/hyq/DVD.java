package hyq;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DVD {

	private String no;		//编号
	private String name;	//姓名
	private int state;		//0.可借 1.已借出
	private Date outDate;	//借出时间
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		if(state !=0 && state != 1){
			this.state = 1;
		}
		else{
			this.state = state;
		}
	}
	public String getOutDate() {
		if(this.outDate!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(outDate);
		}
		else{
			return "";
		}
		
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
}
