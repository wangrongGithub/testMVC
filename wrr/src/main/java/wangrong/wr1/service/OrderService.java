package wangrong.wr1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wangrong.wr1.dao.MiaoshaDao;
import wangrong.wr1.dao.orderDao;
import wangrong.wr1.domain.msorder;
import wangrong.wr1.domain.order;

@Service
public class OrderService {
	@Autowired
	orderDao os;
	
	public msorder getOrderByUidOid(long uid, long oid)
	{
		msorder mo=os.getOrderByUidOid(uid,oid);
		if(mo==null)
			{
			
			return null;
			}
		return mo;
	}
	public order getOrderByOid(long oid)
	{
		order mo=os.getOrderByOid(oid);
		if(mo==null)
		{

			return null;
		}
		return mo;
	}
}
