package DAOs;

import AAE.Eleicao;
import AAE.EleicaoLegislativa;
import AAE.EleicaoPresidencial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("Duplicates")
public class EleicaoDAO implements Map<String, Eleicao> {
    
    public Connection conn;
    
    public void clear () {       
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Eleicao");
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
            PreparedStatement ps = conn.prepareStatement("Select Nome from eleicao where nome = '" + (String)key + "'" );
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
    
    public Set<Map.Entry<String,Eleicao>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Eleicao>> entrySet() not implemented!");
    }
    
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    public Eleicao get(Object key) {
        Eleicao c = null;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from eleicao where nome = '" + key +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String tipo = rs.getString(2);

                if (tipo.equalsIgnoreCase("legislativas"))
                	c = new EleicaoLegislativa(rs.getString(1),rs.getDate("data"));
                else
                	c = new EleicaoPresidencial(rs.getString(1),rs.getDate("data"));
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
        return c;
    }
    
    public int hashCode() {
        return this.conn.hashCode();
    }
    
    public boolean isEmpty() {
        boolean b = false;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Lista");
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
    
    public Set<String> keySet() {
        Set<String> res = new HashSet<String>();
        
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT Nome FROM Eleicao");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getString("Nome"));
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
    
    public Eleicao put(String key, Eleicao value) {
        String tipo = value.getTipo();
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM eleicao WHERE nome='"+key+"' and tipo='"+ tipo+"'");
            
            ps.executeUpdate();
            PreparedStatement sql =conn.prepareStatement("INSERT INTO eleicao VALUES ('"+key+"','"+tipo+"','"+value.getData()+"')");
            sql.executeUpdate();
           } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace();
                return null;
            } 
        }
        return value;
         
    }

    public void putAll(Map<? extends String,? extends Eleicao> t) {
        throw new NullPointerException("Not implemented!");
    }
    
    public Eleicao remove(Object key) {
        throw new NullPointerException("public Eleicao remove(Object key)  not implemented!");
    }
    
    public void remove(String Key) {
        throw new NullPointerException("public void remove(Object key)  not implemented!");}
    
    public int size() {
        int i = -1;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select count(Nome) from eleicao");
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
             
    public Collection<Eleicao> values() {
        Collection<Eleicao> res = new HashSet<Eleicao>();
       
        try { 
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Eleicao");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();) {
                 String tipo = rs.getString(2);
                
                if (tipo.equalsIgnoreCase("legislativas"))
                	res.add(new EleicaoLegislativa(rs.getString("Nome"),rs.getDate("Data")));
                else
                	res.add(new EleicaoPresidencial(rs.getString("Nome"),rs.getDate("Data")));
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

    public Integer getTotalVotos(String nome) {
        Integer res = -1;
        try {
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT Sum(`Nr de Votos`) FROM VotosAdquiridos where VotosAdquiridos.AV_Eleicao = '" + nome + "';");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1);
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
}

