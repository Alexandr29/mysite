package com.nixsolutions.service.jdbc;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.File;

public class AbstractJdbcDaoTest extends DBTestCase {

    //
    //    @Override public void setUp() throws Exception {
    //        ResourceBundle resourceBundle = ResourceBundle.getBundle("h2");
    //        System.setProperty(
    //                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
    //                resourceBundle.getString("jdbc.driver"));
    //        System.setProperty(
    //                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
    //                resourceBundle.getString("jdbc.url"));
    //        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
    //                resourceBundle.getString("jdbc.password"));
    //        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
    //                resourceBundle.getString("jdbc.username"));
    //        //createTables();
    //        super.setUp();
    //    }
    //
    //
    //
    //    public AbstractJdbcDaoTest() {
    //    }
    //
    //    public void testUpdate() throws Exception {
    //        JdbcUserDao userDao = new JdbcUserDao();
    //
    //        Role role = new Role(1L, "Admin");
    //        User user = new User(2L, "upd", "upd", "upd",
    //                "upd", "upd", Date.valueOf("2222-02-22"), role.getId());
    //
    //        userDao.update(user);
    //        IDataSet actualDataSet = getConnection().createDataSet();
    //        ITable actualTable = actualDataSet.getTable("USER");
    //        IDataSet expectedDataSet = new FlatXmlDataSet(new File("src/test/resources/testDataSetUpdate.xml"));
    //        ITable expectedTable = expectedDataSet.getTable("USER");
    //        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
    //                expectedTable.getTableMetaData().getColumns());
    //
    //
    //        Assertion.assertEquals(expectedTable, filteredActualTable);
    //    }
    //    public void testRemove() throws Exception {
    //        JdbcUserDao userDao = new JdbcUserDao();
    //        User user = new User(2L, "Alex2", "alex2", "alex2@gmail.com",
    //                "Alexandr2", "Sinkevich2", Date.valueOf("1997-04-29"),1L);
    //
    //        userDao.remove(user);
    //        IDataSet actualDataSet = getConnection().createDataSet();
    //        ITable actualTable = actualDataSet.getTable("USER");
    //        IDataSet expectedDataSet = new FlatXmlDataSet(
    //                new File("src/test/resources/testDataSetRemove.xml"));
    //        ITable expectedTable = expectedDataSet.getTable("USER");
    //
    //        Assertion.assertEquals(expectedTable, actualTable);
    //    }
    //
    //    public void testFindAll() {
    //        JdbcUserDao userDao = new JdbcUserDao();
    //        List<User> expectedList = new ArrayList<>();
    //        expectedList
    //                .add(new User(1L, "Alex", "1234", "alex@gmail.com", "Alexandr",
    //                        "Sinkevich", Date.valueOf("1997-04-29"), 1L));
    //        expectedList.add(new User(2L, "Alex2", "alex2", "alex2@gmail.com",
    //                "Alexandr2", "Sinkevich2", Date.valueOf("1997-04-29"), 2L));
    //        assertEquals(expectedList.toString(), userDao.findAll().toString());
    //    }
    //
    //    public void testFindByLoginTest() {
    //        JdbcUserDao userDao = new JdbcUserDao();
    //        Role role = new Role(1l, "admin");
    //        User expectedUser = new User(1L, "Alex", "1234", "alex@gmail.com",
    //                "Alexandr", "Sinkevich", Date.valueOf("1997-04-29"), role.getId());
    //        assertEquals(expectedUser.toString(),
    //                userDao.findByLogin("Alex").toString());
    //    }
    //
    @Override protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(
                new File("src/test/resources/testDataSet.xml"));
    }
}
