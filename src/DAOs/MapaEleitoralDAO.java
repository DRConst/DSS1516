package DAOs;

import AAE.AssembleiaDeVoto;
import AAE.MapaEleitoral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by NoobLevler on 29/12/2015.
 */
@SuppressWarnings("Duplicates")
public class MapaEleitoralDAO implements Map<Integer, MapaEleitoral> {

    public Connection conn;

    public void clear () {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `Mapa Legislativo`");
            ps.executeUpdate();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean containsKey(Object key) throws NullPointerException {
        boolean c = false;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select ML_id from `Mapa Legislativo` where ML_id = '" + (Integer)key + "'" );
            ResultSet rs = ps.executeQuery();
            c = rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object value) not implemented!");
    }

    public Set<Entry<Integer,MapaEleitoral>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Equipa>> entrySet() not implemented!");
    }

    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    public MapaEleitoral get(Object key) {
        MapaEleitoral av = null;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from `Mapa Eleitoral` where codigo'" +(Integer)key +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                av = new MapaEleitoral(rs.getInt("ML_id"),rs.getString("Distrito"),rs.getInt("Nr de deputados"),rs.getInt("Nr de eleitores"), rs.getString("eleicao"));
            }
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return av;
    }

    public int hashCode() {
        return this.conn.hashCode();
    }

    public boolean isEmpty() {
        boolean b = false;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Mapa Legislativo`");
            ResultSet rs = ps.executeQuery();
            b = !rs.next();
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public Set<Integer> keySet() {
        Set<Integer> res = new HashSet<>();

        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT ML_id FROM `Mapa Legislativo`");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getInt("ML_id"));
            }
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public MapaEleitoral put(Integer key, MapaEleitoral value) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `Mapa Legislativo` WHERE codigo='"+key+"'");
            ps.executeUpdate();
            PreparedStatement sql =conn.prepareStatement("INSERT INTO `Mapa Legislativo` VALUES ('"+key+"','"+value.getEleitores()+"','"+value.getDeputados()+"','"+value.getDistrito()+"','"+value.getEleicao()+"')");
            sql.executeUpdate();
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new MapaEleitoral(value.getId(),value.getDistrito(), value.getDeputados(),  value.getEleitores(), value.getEleicao());
    }

    public void putAll(Map<? extends Integer,? extends MapaEleitoral> t) {
        throw new NullPointerException("Not implemented!");
    }

    public MapaEleitoral remove(Object key) {
        throw new NullPointerException("public MapaEleitoral remove(Object key)  not implemented!");
    }


    public void remove(Integer cod) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `Mapa Legislativo` WHERE codigo =' "+cod+"'");
            ps.executeUpdate();
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }}

    public int size() {
        int i = -1;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select count(ML_id) from `Mapa Legislativo`");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                i=rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public Collection<MapaEleitoral> values() {
        Collection<MapaEleitoral> res = new ArrayList<>();

        try {
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Mapa Legislativo`");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();) {
                res.add(new MapaEleitoral(rs.getInt("ML_id"),rs.getString("Distrito"),rs.getInt("Nr de deputados"),rs.getInt("Nr de eleitores"), rs.getString("eleicao")));
            }
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public ArrayList<String> getAssembleiasDeVoto (String eleicao) throws SQLException{
        ArrayList<String> res = new ArrayList<String>();
        try{
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select codigo from `assembleia de voto` where eleicao ='" + eleicao +"'");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getString("codigo"));
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }
}
