package com.example.delivery_service;

import java.sql.*;
import java.util.ArrayList;

import static com.example.delivery_service.EntryController.getUserLogin;

public class DatabaseConnection{
        private static com.example.delivery_service.DatabaseConnection instance;
        private Connection connection;


        private DatabaseConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2280_delivery_service",
                    "std_2280_delivery_service", "LizaKorneeva_Liza");
        }

        public Connection getConnection() {
            return connection;
        }

        public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
            if (instance == null) {
                instance = new com.example.delivery_service.DatabaseConnection();
            } else if (instance.getConnection().isClosed()) {
                instance = new com.example.delivery_service.DatabaseConnection();
            }
            return instance;
        }

        public void registerUser(Client client) throws SQLException {
            String query = "INSERT INTO users(login, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            statement.executeUpdate();

            int id = getIdUser(client.getLogin());

            String query2 = "INSERT INTO clients(name, number, address, nearest_delivery_centers_id, users_id) VALUES " +
                    "(?, ?, ?, NULL, ?)";

            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, client.getName());
            statement2.setString(2, client.getNumber());
            statement2.setString(3, client.getAddress());
            statement2.setInt(4, id);
            statement2.executeUpdate();


            String query3 = "INSERT INTO users_roles(roles_id, users_id) VALUES " +
                    "(?, ?)";

            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setInt(1, 1);
            statement3.setInt(2, id);

            statement3.executeUpdate();
        }

        public ResultSet getUser(Client client) throws SQLException {
            String query = "SELECT * FROM users WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        }

        public boolean isLoginExists(String login) throws SQLException {
            String query = "SELECT COUNT(*) FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        }

        public boolean checkNumber(String number) throws SQLException {
            String query = "SELECT number FROM clients WHERE number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            if (resultSet.next())
                count++;

            String query1 = "SELECT number FROM couriers WHERE number = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, number);
            ResultSet resultSet1 = statement1.executeQuery();

            if (resultSet1.next())
                count++;

            String query2 = "SELECT number FROM couriers WHERE number = ?";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, number);
            ResultSet resultSet2 = statement2.executeQuery();

            if (resultSet2.next())
                count++;
            return count != 0;

        }

        public boolean checkNumberName(String number, String name) throws SQLException {
            String query = "SELECT name, number FROM clients WHERE number = ? and name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, number);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }

        public int makeOrder(Parcle parcle) throws SQLException {

            int users_senders_id = getIdUser(getUserLogin());

            String query = "SELECT nearest_delivery_centers_id, id FROM clients WHERE name = ? and number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, parcle.getRecipientName());
            statement.setString(2, parcle.getRecipientNumber());
            ResultSet resultSet = statement.executeQuery();
            int delivery_centers_id = 0;
            int recipients = 0;
            if (resultSet.next()){
                delivery_centers_id = resultSet.getInt("nearest_delivery_centers_id");
                recipients = resultSet.getInt("id");
            }


            String query2 = "SELECT id FROM couriers WHERE delivery_centers_id = ? ORDER BY rand() LIMIT 1";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setInt(1, delivery_centers_id);
            ResultSet resultSet2 = statement.executeQuery();
            int couries_id = 0;
            if (resultSet2.next()){
                couries_id= resultSet2.getInt("id");
            }


            String query3 = "INSERT INTO parcels(couriers_id, typeDelivery, weight, data, status) VALUES (?, ?, ?, ?, 'Оформлена')";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setInt(1, couries_id);
            statement3.setString(2, parcle.getTypeDelivery());
            statement3.setDouble(3, Double.valueOf(parcle.getWeight()));
            statement3.setString(4, parcle.getData());
            statement3.executeUpdate();

            String query5 = "SELECT id FROM parcels WHERE couriers_id = ? and typeDelivery = ? and weight = ? and data = ? and status = 'Оформлена'";
            PreparedStatement statement5 = connection.prepareStatement(query5);
            statement5.setInt(1, couries_id);
            statement5.setString(2, parcle.getTypeDelivery());
            statement5.setDouble(3, Double.valueOf(parcle.getWeight()));
            statement5.setString(4, parcle.getData());

            ResultSet resultSet5 = statement5.executeQuery();
            int parcels_id = 0;
            if (resultSet5.next()){
                parcels_id = resultSet5.getInt(1);
            }

            String query10 = "SELECT clients.id FROM clients JOIN users on users.id = clients.users_id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, users_senders_id);
            ResultSet resultSet10 = statement10.executeQuery();
            int senders_id = 0;
            if (resultSet10.next()){
                senders_id = resultSet10.getInt(1);
            }


            String query6 = "INSERT INTO senders(clients_id, parcels_id) VALUES (?, ?)";
            PreparedStatement statement6 = connection.prepareStatement(query6);
            statement6.setInt(1, senders_id);
            statement6.setInt(2, parcels_id);
            statement6.executeUpdate();

            String query4 = "INSERT INTO recipients (clients_id, parcels_id) VALUES (?, ?)";
            PreparedStatement statement4 = connection.prepareStatement(query4);
            statement4.setInt(1, recipients);
            statement4.setInt(2, parcels_id);
            statement4.executeUpdate();

            String query9 = "SELECT delivery_centers.id FROM delivery_centers JOIN clients on clients.nearest_delivery_centers_id = .delivery_centers.id " +
                    "WHERE SUBSTRING_INDEX(delivery_centers.address, ',', 1) = SUBSTRING_INDEX(clients.address, ',', 1) AND clients.id = ? ORDER BY rand() LIMIT 1";
            PreparedStatement statement9 = connection.prepareStatement(query9);
            statement9.setInt(1, senders_id);
            ResultSet resultSet9 = statement9.executeQuery();
            int centers_id = 0;
            if (resultSet9.next()){
                centers_id= resultSet9.getInt("id");
            }


            String query7 = "INSERT INTO receiving_сenters(parcels_id, delivery_centers_id) VALUES (?, ?)";
            PreparedStatement statement7 = connection.prepareStatement(query7);
            statement7.setInt(1, parcels_id);
            statement7.setInt(2, delivery_centers_id);
            statement7.executeUpdate();


            String query8 = "INSERT INTO departure_centers(parcels_id, delivery_centers_id) VALUES (?, ?)";
            PreparedStatement statement8 = connection.prepareStatement(query8);
            statement8.setInt(1, parcels_id);
            statement8.setInt(2, centers_id);
            statement8.executeUpdate();


            return parcels_id;
        }

        public int getIdUser(String login) throws SQLException {
            String query = "SELECT id FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            int id = 0;
            if (result.next())
                id = result.getInt("id");
            return id;
        }


        public boolean checkLogin(String number) throws SQLException {
            String query = "SELECT users.id FROM users JOIN clients on clients.users_id = users.id WHERE number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, number);
            ResultSet result = statement.executeQuery();
            return result.next() && result.getInt(1) == getIdUser(getUserLogin());
        }

        public ArrayList<String> parcleStatus() throws SQLException {
            int users_senders_id = getIdUser(getUserLogin());

            String query10 = "SELECT clients.id FROM clients JOIN users on users.id = clients.users_id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, users_senders_id);
            ResultSet resultSet10 = statement10.executeQuery();
            int senders_id = 0;
            if (resultSet10.next()){
                senders_id = resultSet10.getInt(1);
            }

            String query = "SELECT parcels.id, parcels.data, clients.name, clients.number, parcels.status FROM parcels JOIN recipients ON " +
                    "recipients.parcels_id = parcels.id JOIN clients on recipients.clients_id = clients.id JOIN senders ON " +
                    "senders.parcels_id = parcels.id WHERE senders.clients_id = ? " +
                    "ORDER BY parcels.data DESC";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, senders_id);
            ResultSet result = statement.executeQuery();
            ArrayList<String> p = new ArrayList<String>();
            while (result.next()){
                p.add(result.getString(1) + "*" + result.getString(2) + "*" +
                result.getString(3) + "*" + result.getString(4) + '*' + result.getString(5));
            }
            return p;
        }

        public int checkRole(String login) throws SQLException {
            int users_senders_id = getIdUser(login);

            String query = "SELECT roles_id FROM users_roles  WHERE users_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, users_senders_id);
            ResultSet result = statement.executeQuery();
            int role = 0;
            if (result.next())
                role = result.getInt("roles_id");
            return role;
        }

        public ArrayList<String> dataManager() throws SQLException {
            int users_manager_id = getIdUser(getUserLogin());


            String query10 = "SELECT managers.id FROM managers JOIN users on users.id = managers.users_id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, users_manager_id);
            ResultSet resultSet10 = statement10.executeQuery();
            int manager_id = 0;
            if (resultSet10.next()){
                manager_id = resultSet10.getInt(1);
            }

            String query = "SELECT parcels.id, parcels.weight, parcels.typeDelivery, parcels.data, delivery_centers.name" +
                    " FROM parcels" +
                    " INNER JOIN departure_centers ON departure_centers.parcels_id = parcels.id" +
                    " INNER JOIN managers ON managers.delivery_centers_id = departure_centers.delivery_centers_id" +
                    " INNER JOIN recipients ON recipients.parcels_id = parcels.id" +
                    " INNER JOIN clients ON clients.id = recipients.clients_id" +
                    " INNER JOIN delivery_centers ON clients.nearest_delivery_centers_id = delivery_centers.id" +
                    " WHERE managers.id = ? and parcels.status = 'Оформлена'" +
                    " ORDER BY parcels.data DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, manager_id);
            ResultSet result = statement.executeQuery();
            ArrayList<String> p = new ArrayList<String>();
            while (result.next()){
                p.add(result.getString(1) + "*" + result.getString(2) + "*" +
                        result.getString(3) + "*" + result.getString(4) + '*' + result.getString(5));
            }
            return p;
        }

        public void parcleStatusManager(String id) throws SQLException {
            int id_ = Integer.parseInt(id);
            String query6 = "UPDATE parcels SET status = 'Отправлена' WHERE id = ?";
            PreparedStatement statement6 = connection.prepareStatement(query6);
            statement6.setInt(1, id_);
            statement6.executeUpdate();
        }

        public void registerCourier(Courier courier) throws SQLException {
            String query = "INSERT INTO users(login, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courier.getLogin());
            statement.setString(2, courier.getPassword());
            statement.executeUpdate();

            int idUser = getIdUser(courier.getLogin());

            String query1 = "SELECT id FROM delivery_centers WHERE name = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, courier.getCenter_delivery());
            ResultSet resultSet1 = statement1.executeQuery();
            int delivery_centers_id = 0;
            if (resultSet1.next()){
                delivery_centers_id = resultSet1.getInt("id");
            }

            String query2 = "INSERT INTO couriers(delivery_centers_id, users_id, name, number) VALUES " +
                    "(?, ?, ?, ?)";

            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setInt(1, delivery_centers_id);
            statement2.setInt(2, idUser);
            statement2.setString(3, courier.getName());
            statement2.setString(4, courier.getNumber());
            statement2.executeUpdate();


            String query3 = "INSERT INTO users_roles(roles_id, users_id) VALUES " +
                    "(?, ?)";

            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setInt(1, 3);
            statement3.setInt(2, idUser);

            statement3.executeUpdate();
        }

        public void parcleStatusCourier(String id) throws SQLException {
            int id_ = Integer.parseInt(id);
            String query6 = "UPDATE parcels SET status = 'У курьера' WHERE id = ?";
            PreparedStatement statement6 = connection.prepareStatement(query6);
            statement6.setInt(1, id_);
            statement6.executeUpdate();
        }

        public ArrayList<String> dataCourier() throws SQLException {
            int users_courier_id = getIdUser(getUserLogin());

            String query10 = "SELECT couriers.id FROM couriers JOIN users on users.id = couriers.users_id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, users_courier_id);
            ResultSet resultSet10 = statement10.executeQuery();
            int courier_id = 0;
            if (resultSet10.next()){
                courier_id = resultSet10.getInt(1);
            }

            String query = "SELECT parcels.id, clients.name, clients.number, clients.address" +
                    " FROM parcels INNER JOIN recipients ON recipients.parcels_id = parcels.id" +
                    " INNER JOIN clients ON recipients.clients_id = clients.id" +
                    " WHERE parcels.couriers_id = ? and parcels.status = 'Отправлена'" +
                    " ORDER BY parcels.data DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, courier_id);
            ResultSet result = statement.executeQuery();
            ArrayList<String> p = new ArrayList<String>();
            while (result.next()){
                p.add(result.getString(1) + "*" + result.getString(2) + "*" +
                        result.getString(3) + "*" + result.getString(4));
            }
            return p;
        }

        public ArrayList<String> deleteCourier() throws SQLException {
            String query = "SELECT couriers.name, couriers.number, users.login, delivery_centers.name" +
                    " FROM couriers INNER JOIN users ON couriers.users_id = users.id" +
                    " INNER JOIN delivery_centers ON delivery_centers.id = couriers.delivery_centers_id" +
                    " ORDER BY couriers.name;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            ArrayList<String> p = new ArrayList<String>();
            while (result.next()){
                p.add(result.getString(1) + "*" + result.getString(2) + "*" +
                        result.getString(3) + "*" + result.getString(4));
            }
            return p;
        }

        public ArrayList<String> deleteManager() throws SQLException {
            String query = "SELECT managers.name, users.login, delivery_centers.name" +
                    " FROM managers INNER JOIN users ON managers.users_id = users.id" +
                    " INNER JOIN delivery_centers ON delivery_centers.id = managers.delivery_centers_id" +
                    " ORDER BY managers.name;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            ArrayList<String> p = new ArrayList<String>();
            while (result.next()){
                p.add(result.getString(1) + "*" + result.getString(2) + "*" +
                        result.getString(3));
            }
            return p;
        }

        public boolean checkDelivery_center(String name) throws SQLException {
            String query = "SELECT id FROM delivery_centers  WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            return result.next();
        }

        public void registerManager(Manager manager) throws SQLException {
            String query = "INSERT INTO users(login, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, manager.getLogin());
            statement.setString(2, manager.getPassword());
            statement.executeUpdate();

            int idUser = getIdUser(manager.getLogin());


            String query10 = "SELECT id FROM delivery_centers WHERE name = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setString(1, manager.getDelivery_center());
            ResultSet resultSet10 = statement10.executeQuery();
            int delivery_centers_id = 0;
            if (resultSet10.next()){
                delivery_centers_id = resultSet10.getInt("id");
            }

            String query2 = "INSERT INTO managers(delivery_centers_id, name, users_id) VALUES " +
                    "(?, ?, ?)";

            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setInt(1, delivery_centers_id);
            statement2.setString(2, manager.getName());
            statement2.setInt(3, idUser);
            statement2.executeUpdate();


            String query3 = "INSERT INTO users_roles(roles_id, users_id) VALUES " +
                    "(?, ?)";

            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setInt(1, 2);
            statement3.setInt(2, idUser);

            statement3.executeUpdate();
        }

        public ArrayList<String> recepient_parcle() throws SQLException {
            int users_recepient_id = getIdUser(getUserLogin());

            String query10 = "SELECT clients.id FROM clients JOIN users on users.id = clients.users_id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, users_recepient_id);
            ResultSet resultSet10 = statement10.executeQuery();
            int recepient_id = 0;
            if (resultSet10.next()){
                recepient_id = resultSet10.getInt(1);
            }

            String query = "SELECT parcels.id, parcels.data, clients.name, clients.number " +
                    "FROM parcels " +
                    "JOIN recipients ON recipients.parcels_id = parcels.id " +
                    "JOIN senders ON senders.parcels_id = parcels.id " +
                    "JOIN clients ON clients.id = senders.clients_id " +
                    "WHERE recipients.clients_id = ? and parcels.status = 'У курьера'" +
                    "ORDER BY parcels.data DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recepient_id);
            ResultSet result = statement.executeQuery();
            ArrayList<String> p = new ArrayList<String>();
            while (result.next()){
                p.add(result.getString(1) + "*" + result.getString(2) + "*" +
                        result.getString(3) + "*" + result.getString(4));
            }
            return p;
        }

        public void recipientStatusParcle(String id) throws SQLException {
            int id_ = Integer.parseInt(id);
            String query6 = "UPDATE parcels SET status = 'Получена' WHERE id = ?";
            PreparedStatement statement6 = connection.prepareStatement(query6);
            statement6.setInt(1, id_);
            statement6.executeUpdate();
        }


        public boolean checkNumberUpdate(String number) throws SQLException {
            int id_user = getIdUser(getUserLogin());

            String query10 = "SELECT clients.id FROM clients JOIN users on clients.users_id = users.id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, id_user);
            ResultSet resultSet10 = statement10.executeQuery();
            int client_id = 0;
            if (resultSet10.next())
                client_id = resultSet10.getInt(1);

            String query = "SELECT number FROM clients WHERE number = ? and id NOT IN (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, number);
            statement.setInt(2, client_id);
            ResultSet resultSet = statement.executeQuery();

            String query1 = "SELECT number FROM couriers WHERE number = ? and id NOT IN (?)";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, number);
            statement1.setInt(2, client_id);
            ResultSet resultSet1 = statement1.executeQuery();

            String query2 = "SELECT number FROM couriers WHERE number = ? and id NOT IN (?)";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, number);
            statement2.setInt(2, client_id);
            ResultSet resultSet2 = statement2.executeQuery();

            int count = 0;

            if (resultSet.next()){
                count++;
            }

            if (resultSet1.next()){
                count++;
            }
            if (resultSet2.next()){
                count++;
            }
            return count != 0;

        }

        public boolean isLoginExistsUPDATE(String login) throws SQLException {
            int id_user = getIdUser(getUserLogin());
            String query = "SELECT COUNT(*) FROM users WHERE login = ? and id NOT IN (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setInt(2, id_user);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        }

        public void updateUser(Client client) throws SQLException {
            int id_user = getIdUser(getUserLogin());

            String query10 = "SELECT clients.id FROM clients JOIN users on clients.users_id = users.id WHERE users.id = ?";
            PreparedStatement statement10 = connection.prepareStatement(query10);
            statement10.setInt(1, id_user);
            ResultSet resultSet10 = statement10.executeQuery();
            int client_id = 0;
            if (resultSet10.next())
                client_id = resultSet10.getInt(1);

            String query3 = "SELECT address FROM clients WHERE id = ?";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setInt(1, client_id);
            ResultSet resultSet3 = statement3.executeQuery();
            String address = "";
            if (resultSet3.next())
                address = resultSet3.getString(1);


            String query = "UPDATE users SET login = ?, password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            statement.setInt(3, id_user);
            statement.executeUpdate();

            String new_address = client.getAddress();

            if (!address.equals(new_address)){
                String query2 = "UPDATE clients SET name = ?, number = ?, address = ?, " +
                        "nearest_delivery_centers_id = NULL WHERE id = ? and users_id = ? ";

                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, client.getName());
                statement2.setString(2, client.getNumber());
                statement2.setString(3, client.getAddress());
                statement2.setInt(4, client_id);
                statement2.setInt(5, id_user);
                statement2.executeUpdate();
            }
            else if(address.equals(new_address)){
                String query2 = "UPDATE clients SET name = ?, number = ?, address = ? " +
                        "WHERE id = ? and users_id = ? ";

                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, client.getName());
                statement2.setString(2, client.getNumber());
                statement2.setString(3, client.getAddress());
                statement2.setInt(4, client_id);
                statement2.setInt(5, id_user);
                statement2.executeUpdate();
            }
        }

    public ArrayList<String> name_delivery_center() throws SQLException {
        String query10 = "SELECT name FROM delivery_centers";
        PreparedStatement statement10 = connection.prepareStatement(query10);
        ResultSet resultSet10 = statement10.executeQuery();
        ArrayList<String> p = new ArrayList<String>();
        while (resultSet10.next()){
            p.add(resultSet10.getString(1));
        }
        return p;
    }



}