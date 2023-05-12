package ptithcm.HTTPRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ptithcm.entity.DSTAIKHOANk;
import ptithcm.entity.HOADONk;
import ptithcm.entity.KHACHHANGk;
import ptithcm.entity.SANPHAMk;

public class BillAPI {
	static HttpURLConnection con;
	public static List<HOADONk> getAllHoaDon()
	{
		List<HOADONk> list = null;
		String str= pubVar.apiString+"findAllBill";
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
					list = Arrays.asList(mapper.readValue(response.toString(), HOADONk[].class));
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static HOADONk getHoaDon(String id)
	{
		HOADONk list = null;
		String str= pubVar.apiString+"/findOneBill/{"+id+"}";
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
					list = mapper.readValue(response.toString(), HOADONk.class);
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static int findMaxBillID()
	{
		int list = -1;
		String str= pubVar.apiString+"findMaxBillID";
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
					list = mapper.readValue(response.toString(), Integer.class);
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
