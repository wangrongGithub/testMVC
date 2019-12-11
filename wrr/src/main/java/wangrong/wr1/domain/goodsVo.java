package wangrong.wr1.domain;

import java.util.Date;

public class goodsVo extends goods{
	 long  msg_id;
      double msg_price; 
      long msg_stock;  
          Date msg_start; 
          Date msg_end;
		public long getMsg_id() {
			return msg_id;
		}
		public void setMsg_id(long msg_id) {
			this.msg_id = msg_id;
		}
		public double getMsg_price() {
			return msg_price;
		}
		public void setMsg_price(double msg_price) {
			this.msg_price = msg_price;
		}
		public long getMsg_stock() {
			return msg_stock;
		}
		public void setMsg_stock(long msg_stock) {
			this.msg_stock = msg_stock;
		}
		public Date getMsg_start() {
			return msg_start;
		}
		public void setMsg_start(Date msg_start) {
			this.msg_start = msg_start;
		}
		public Date getMsg_end() {
			return msg_end;
		}
		public void setMsg_end(Date msg_end) {
			this.msg_end = msg_end;
		}

	@Override
	public String toString() {
		return "goodsVo{" +
				"msg_id=" + msg_id +
				", msg_price=" + msg_price +
				", msg_stock=" + msg_stock +
				", msg_start=" + msg_start +
				", msg_end=" + msg_end +
				'}';
	}
}
