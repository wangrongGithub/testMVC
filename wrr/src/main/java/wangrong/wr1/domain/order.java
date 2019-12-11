package wangrong.wr1.domain;

import java.util.Date;

public class order {
	
	long o_id;   
	String g_name; 
	long g_id; 
	long u_id;   
       String o_address;  
       double g_price; 
        long  g_count;  
         int o_status;   
          Date o_createdate;  
          Date  o_paydate;    
           int   o_channel;
		public long getO_id() {
			return o_id;
		}
		public void setO_id(long o_id) {
			this.o_id = o_id;
		}
		public String getG_name() {
			return g_name;
		}
		public void setG_name(String g_name) {
			this.g_name = g_name;
		}
		public long getG_id() {
			return g_id;
		}
		public void setG_id(long g_id) {
			this.g_id = g_id;
		}
		public long getU_id() {
			return u_id;
		}
		public void setU_id(long u_id) {
			this.u_id = u_id;
		}
		public String getO_address() {
			return o_address;
		}
		public void setO_address(String o_address) {
			this.o_address = o_address;
		}
		public double getG_price() {
			return g_price;
		}
		public void setG_price(double g_price) {
			this.g_price = g_price;
		}
		public long getG_count() {
			return g_count;
		}
		public void setG_count(long g_count) {
			this.g_count = g_count;
		}
		public int getO_status() {
			return o_status;
		}
		public void setO_status(int o_status) {
			this.o_status = o_status;
		}
		public Date getO_createdate() {
			return o_createdate;
		}
		public void setO_createdate(Date o_createdate) {
			this.o_createdate = o_createdate;
		}
		public Date getO_paydate() {
			return o_paydate;
		}
		public void setO_paydate(Date o_paydate) {
			this.o_paydate = o_paydate;
		}
		public int getO_channel() {
			return o_channel;
		}
		public void setO_channel(int o_channel) {
			this.o_channel = o_channel;
		}
           
           

}
