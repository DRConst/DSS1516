package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class AssembleiaDeVotoDAO implements Map<String,AssembleiaDeVoto> {
    
    public Connection conn;
    
    public void clear () {       
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `Assembleia de Voto`");
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
            PreparedStatement ps = conn.prepareStatement("Select codigo from `equipas` where codigo = '" + (String)key + "'" );
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
    
    public Set<Map.Entry<String,AssembleiaDeVoto>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Equipa>> entrySet() not implemented!");
    }
    
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    public AssembleiaDeVoto get(Object key) {
        ArrayList<Eleitor> el = new ArrayList<Eleitor>();
        AssembleiaDeVoto av = null;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from `assembleia de voto` where codigo'" +(String)key +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            String c = rs.getString("Codigo");
                PreparedStatement ps1 = conn.prepareStatement("Select * from `Eleitor` where assembleia de voto = '" + c +"'" );
            	ResultSet rs2 = ps1.executeQuery();
            	for(;rs2.next();){
                    Eleitor e = new Eleitor(rs2.getString("Nr de Eleitor"),rs2.getString("Nome"),rs2.getInt("Idade"),rs2.getDate("Data Recensiamento"),rs2.getString("Distrito"),rs2.getString("Concelho"),rs2.getString("Freguesia"));
                    el.add(e);
                }
                av = new AssembleiaDeVoto(rs.getString("Codigo"),rs.getString("Concelho"),rs.getString("Freguesia"),rs.getDate("Hora de Abertura"),rs.getDate("Hora de Encerramento"),rs.getString("Local"),rs.getInt("Eleitores Inscritos"),rs.getInt("Nr de votantes"),rs.getInt("Votos em Branco"),rs.getInt("Votos Nulos"),rs.getInt("Nr de Reclamacoes"), el);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `assembleia de voto`");
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
            PreparedStatement ps = conn.prepareStatement("SELECT codigo FROM `assembleia de voto`");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getString("Codigo"));
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
    
    public AssembleiaDeVoto put(String key, AssembleiaDeVoto value) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `assembleia de voto` WHERE codigo='"+key+"' AND eleicao ='"+value.getEleicao()+"'");
            ps.executeUpdate();
            PreparedStatement sql =conn.prepareStatement("INSERT INTO  VALUES ('"+key+"','"+value.getEleicao()+"','"+value.getConcelho()+"','"+value.getFreguesia()+"','"+value.getHoraA()+"','"+value.getHoraE()+"','"+value.getLocal()+"','"+value.getEleitoresIns()+"','"+value.getnrVotantes()+"','"+value.getVotosBr()+"','"+value.getVotosNulos()+"','"+value.getnrReclamacoes()+"')");
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
         return new AssembleiaDeVoto(key, value.getEleicao(), value.getConcelho(), value.getFreguesia(), value.getHoraA(), value.getHoraE(), value.getLocal(), value.getEleitoresIns(), value.getnrVotantes(), value.getVotosBr(), value.getVotosNulos(), value.getnrReclamacoes());
    }

    public void putAll(Map<? extends String,? extends AssembleiaDeVoto> t) {
        throw new NullPointerException("Not implemented!");
    }
    
    public AssembleiaDeVoto remove(Object key) {
        throw new NullPointerException("public AssembleiaDeVoto remove(Object key)  not implemented!");
    }
    
    public void remove(String cod, String el) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `assembleia de voto` WHERE codigo =' "+cod+"' AND eleicao='"+el+"'");
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
            PreparedStatement ps = conn.prepareStatement("Select count(AV_id) from `assembleia de voto`");
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
             
    public Collection<AssembleiaDeVoto> values() {
        Collection<AssembleiaDeVoto> res = new ArrayList<AssembleiaDeVoto>();
        ArrayList<Eleitor> el = new ArrayList<Eleitor>();
       
        try { 
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `assembliea de voto`");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();) {
            	int id = rs.getInt(1);
                PreparedStatement ps1 = conn.prepareStatement("Select * from `Eleitor` where assembleia de voto = '" + id +"'" );
            	ResultSet rs2 = ps1.executeQuery();
            	for(;rs2.next();){
                Eleitor e = new Eleitor(rs2.getString("Nr de Eleitor"),rs2.getString("Nome"),rs2.getInt("Idade"),rs2.getDate("Data Recensiamento"),rs2.getString("Distrito"),rs2.getString("Concelho"),rs2.getString("Freguesia"));
                el.add(e);
                }
                res.add(new AssembleiaDeVoto(rs.getString("Codigo"),rs.getString("Concelho"),rs.getString("Freguesia"),rs.getDate("Hora de Abertura"),rs.getDate("Hora de Encerramento"),rs.getString("Local"),rs.getInt("Eleitores Inscritos"),rs.getInt("Nr de votantes"),rs.getInt("Votos em Branco"),rs.getInt("Votos Nulos"),rs.getInt("Nr de Reclamacoes"));
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

