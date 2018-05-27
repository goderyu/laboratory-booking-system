package dao.proxy;

import dao.ILabCloseSettingDAO;
import dao.impl.LabCloseSettingDAOImpl;
import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import vo.LabCloseSetting;

public class LabCloseSettingDAOProxy implements ILabCloseSettingDAO {
    private DatabaseConnection dbc = null;
    private final ILabCloseSettingDAO dao;

    public LabCloseSettingDAOProxy() {
        try {
            this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new LabCloseSettingDAOImpl(this.dbc.getConnection());
    }

    public boolean insertLabCloseSetting(LabCloseSetting labCloseSetting) {
        boolean flag;
        flag = this.dao.insertLabCloseSetting(labCloseSetting);
        try {
            this.dbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }


}
