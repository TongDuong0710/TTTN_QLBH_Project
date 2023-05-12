package ptithcm.HTTPRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ptithcm.bean.Convert;
import ptithcm.bean.OrderInput;
import ptithcm.entity.DDH;
import ptithcm.entity.KHACHHANG;
import ptithcm.entity.SANPHAM;



public class OrderAPI {
	static HttpURLConnection con;
	public static DDH getOrderByID(Integer MSDDH)
	{
		DDH dh=null;
		OrderInput od=null;
		String str= pubVar.apiString+"order?MSDDH="+MSDDH;
		try {
			URL url = new URL(str);
			con= (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
				if(response.toString().trim().equals("")==false)
				{
					ObjectMapper mapper = new ObjectMapper();
					od= mapper.readValue(response.toString(), OrderInput.class);
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(od!=null)
		{
			dh= Convert.convertToDDH(od);
		}
		return dh;
	}
	public static List<DDH> getHistoryOrder(int maKH)
	{
		List<DDH> list = new ArrayList<DDH>();
		List<OrderInput> listOD=new ArrayList<OrderInput>();
		String str= pubVar.apiString+"getHistoryOrder?maKH="+maKH;
		try {
			URL url = new URL(str);
			con= (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
				if(response.toString().trim().equals("")==false)
				{
					ObjectMapper mapper = new ObjectMapper();
					listOD = Arrays.asList(mapper.readValue(response.toString(), OrderInput[].class));
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(listOD!=null)
		{
			list= Convert.convertToListDDH(listOD);
		}
		return list;
	}
	public static DDH insertOrder(DDH dh)
	{
		OrderInput od= Convert.convertToOrderInput(dh);
		String str= pubVar.apiString+"insertOrder";
		try {
			URL url = new URL(str);
			con= (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			System.out.println("Vo api insert Order");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonInputString = ow.writeValueAsString(od);
//			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//            dos.writeBytes(jsonInputString);
			con.getOutputStream().write(jsonInputString.getBytes());
			System.out.println(jsonInputString);
            int responseCode = con.getResponseCode();
			System.out.println("POST Response KH Code :: " + responseCode);
		} catch (MalformedURLException e) {
			System.out.println("Loi trong api insert order1 "+ e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Loi trong api insert order2 " + e.getMessage());
			//e.printStackTrace();
		}
		return dh;
	}
	public static DDH cancelOrder(DDH dh)
	{
		OrderInput od= Convert.convertToOrderInput(dh);
		String str= pubVar.apiString+"cancelOrder";
		try {
			URL url = new URL(str);
			con= (HttpURLConnection)url.openConnection();
			con.setRequestMethod("PUT");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("Content-Type", "application/json");
			 con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonInputString = ow.writeValueAsString(od);
//			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//            dos.writeBytes(jsonInputString);
			con.getOutputStream().write(jsonInputString.getBytes());
            int responseCode = con.getResponseCode();
			System.out.println("POST Response KH Code :: " + responseCode);
			return dh;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dh;
	}
	
}
