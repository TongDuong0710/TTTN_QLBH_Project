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

public class AccountAPI {
	static HttpURLConnection con;
	public static DSTAIKHOANk getTaiKhoan(String userName)
	{
		DSTAIKHOANk tk=null;
		String str= "http://localhost:8082/account?userName="+userName;
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
					DSTAIKHOANk tkLoaded= mapper.readValue(response.toString(), DSTAIKHOANk.class);
					tk=tkLoaded;
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tk;
	}
	public static boolean saveTaiKhoan(DSTAIKHOANk tk)
	{
		String str= "http://localhost:8082/createAcc";
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

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonInputString = ow.writeValueAsString(tk);
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(jsonInputString);
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}
			System.out.println(jsonInputString);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deleteTaiKhoan(DSTAIKHOANk tk)
	{
		String str= "http://localhost:8082/deleteAcc";
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

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonInputString = ow.writeValueAsString(tk);
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(jsonInputString);
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}
			System.out.println(jsonInputString);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean updateMK(DSTAIKHOANk tk)
	{
		String str= pubVar.apiString+"updateAcc";
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
			String jsonInputString = ow.writeValueAsString(tk);
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(jsonInputString);
			int responseCode = con.getResponseCode();
	
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("PUT request not worked");
			}
			System.out.println(jsonInputString);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<DSTAIKHOANk> getAll()
	{
		List<DSTAIKHOANk> list = null;
		String str= pubVar.apiString+"findTK";
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
					list = Arrays.asList(mapper.readValue(response.toString(), DSTAIKHOANk[].class));
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
