package dao.proxy;

import dao.ILabNameDAO;
import dao.impl.LabNameDAOImpl;
import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import vo.LabName;

import java.util.ArrayList;

public class LabNameDAOProxy implements ILabNameDAO {
    private DatabaseConnection dbc = null;
    private final ILabNameDAO dao;

    public LabNameDAOProxy() {
        try {
            this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new LabNameDAOImpl(this.dbc.getConnection());
    }

    public ArrayList<LabName> selectLabName() {
        ArrayList<LabName> labNameList =new ArrayList<>();
        try {
            labNameList = this.dao.selectLabName();
            this.dbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labNameList;

    }
}
