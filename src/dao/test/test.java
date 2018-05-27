package dao.test;

import java.util.Iterator;
import java.util.List;

import factory.DAOFactory;
import vo.laboratory;

/**
* @author 李浩
* 类说明
*/
public class test {
	public static void main(String[] args) throws Exception {
		List<laboratory> all = DAOFactory.getIResDAOInstance().findAllLab();
		if (all == null) {
			System.out.println("空指针");
		}
		Iterator<laboratory> iter = all.iterator();
		while (iter.hasNext()) {
			laboratory lab = iter.next();
			System.out.println(lab.getLaboratoryID());
		}
	}
}
