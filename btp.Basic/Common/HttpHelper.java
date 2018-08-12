package Common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *   
 * 
 * @Title: HttpHelper.java
 * @Package com.jarvis.base.util
 * @Description:http请求工具类
 * @author Jack 
 * @date 2017年9月2日 下午3:34:49
 * @version V1.0  
 */
public class HttpHelper {

	private static final int DEFAULT_INITIAL_BUFFER_SIZE = 4 * 1024; // 4 kB

	private HttpHelper() {

	}

	/**
	 * 返回对应URL地址的内容，注意，只返回正常响应(状态响应代码为200)的内容
	 * 
	 * @param urlPath
	 *            需要获取内容的URL地址
	 * @return 获取的内容字节数组
	 */
	public static byte[] getURLContent(String urlPath) {
		HttpURLConnection conn = null;
		InputStream inStream = null;
		byte[] buffer = null;

		try {
			URL url = new URL(urlPath);
			HttpURLConnection.setFollowRedirects(false);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setConnectTimeout(10000); // 10秒
			conn.setReadTimeout(60000); // 60秒

			conn.connect();

			int repCode = conn.getResponseCode();
			if (repCode == 200) {
				inStream = conn.getInputStream();
				int contentLength = conn.getContentLength();
				buffer = getResponseBody(inStream, contentLength);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("获取内容失败");
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}

				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("关闭连接失败");
			}
		}
		return buffer;
	}

	/**
	 * 描述：获取BODY部分的字节数组 时间：2010-9-7 下午03:16:46
	 * 
	 * @param instream
	 * @param contentLength
	 * @return
	 * @throws Exception
	 */
	private static byte[] getResponseBody(InputStream instream, int contentLength) throws Exception {
		if (contentLength == -1) {
			System.out.println("Going to buffer response body of large or unknown size. ");
		}
		ByteArrayOutputStream outstream = new ByteArrayOutputStream(
				contentLength > 0 ? (int) contentLength : DEFAULT_INITIAL_BUFFER_SIZE);
		byte[] buffer = new byte[4096];
		int len;
		while ((len = instream.read(buffer)) > 0) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		return outstream.toByteArray();
	}

	@SuppressWarnings("unused")
	private static void readFixedLenToBuffer(InputStream inStream, byte[] buffer) throws Exception {
		int count = 0;
		int remainLength = buffer.length;
		int bufLength = buffer.length;
		int readLength = 0;
		do {
			count = inStream.read(buffer, readLength, remainLength);
			if (count == -1) // 已经到达末尾
			{
				if (readLength != bufLength) // 若实际读取的数据和需要读取的数据不匹配，则报错
				{
					throw new Exception("读取数据出错，不正确的数据结束");
				}
			}

			readLength += count;

			if (readLength == bufLength) // 已经读取完，则返回
			{
				return;
			}

			remainLength = bufLength - readLength;
		} while (true);
	}

	/**
	 * 返回对应URL地址的内容，注意，只返回正常响应(状态响应代码为200)的内容
	 * 
	 * @param urlPath
	 *            需要获取内空的URL地址
	 * @param charset
	 *            字符集编码方式
	 * @return 获取的内容字串
	 */
	public static String getURLContent(String urlPath, String charset) {
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(urlPath);
			HttpURLConnection.setFollowRedirects(false);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setConnectTimeout(10000); // 10秒
			conn.setReadTimeout(60000); // 60秒

			conn.connect();

			int repCode = conn.getResponseCode();
			if (repCode == 200) {
				int count = 0;
				char[] chBuffer = new char[1024];
				BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
				while ((count = input.read(chBuffer)) != -1) {
					buffer.append(chBuffer, 0, count);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("获取内容失败");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("关闭连接失败");
			}
		}

		return buffer.toString();
	}

	public static String getURLContent(String urlPath, String requestData, String charset) {
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		StringBuffer buffer = new StringBuffer();
		OutputStreamWriter out = null;
		try {
			URL url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setConnectTimeout(10000); // 10秒
			conn.setReadTimeout(60000); // 60秒

			out = new OutputStreamWriter(conn.getOutputStream(), charset);
			out.write(requestData);
			out.flush();

			int repCode = conn.getResponseCode();
			if (repCode == 200) {
				int count = 0;
				char[] chBuffer = new char[1024];
				BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
				while ((count = input.read(chBuffer)) != -1) {
					buffer.append(chBuffer, 0, count);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("获取内容失败");
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("关闭连接失败");
			}
		}

		return buffer.toString();
	}


	public static String SendGetRequest(String url) throws Exception {
		return send(url, "GET", null, null);
	}

	public static String SendPostRequest(String url, String param) throws Exception {
		return send(url, "POST", param, null);
	}

	public static String send(String url, String method, String param, Map<String, String> headers) throws Exception {
		String result = null;
		HttpURLConnection conn = getConnection(url, method, param, headers);
		String charset = conn.getHeaderField("Content-Type");
		charset = detectCharset(charset);
		InputStream input = getInputStream(conn);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int count;
		byte[] buffer = new byte[4096];
		while ((count = input.read(buffer, 0, buffer.length)) > 0) {
			output.write(buffer, 0, count);
		}
		input.close();
		// 若已通过请求头得到charset，则不需要去html里面继续查找
		if (charset == null || charset.equals("")) {
			charset = detectCharset(output.toString());
			// 若在html里面还是未找到charset，则设置默认编码为utf-8
			if (charset == null || charset.equals("")) {
				charset = "utf-8";
			}
		}
		result = output.toString(charset);
		output.close();

		return result;
	}

	private static String detectCharset(String input) {
		Pattern pattern = Pattern.compile("charset=\"?([\\w\\d-]+)\"?;?", Pattern.CASE_INSENSITIVE);
		if (input != null && !input.equals("")) {
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	private static InputStream getInputStream(HttpURLConnection conn) throws Exception {
		String ContentEncoding = conn.getHeaderField("Content-Encoding");
		if (ContentEncoding != null) {
			ContentEncoding = ContentEncoding.toLowerCase();
			if (ContentEncoding.indexOf("gzip") != 1)
				return new GZIPInputStream(conn.getInputStream());
			else if (ContentEncoding.indexOf("deflate") != 1)
				return new DeflaterInputStream(conn.getInputStream());
		}

		return conn.getInputStream();
	}

	private static HttpURLConnection getConnection(String url, String method, String param, Map<String, String> header) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
		conn.setRequestMethod(method);

		// 设置通用的请求属性
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36");
		conn.setRequestProperty("Accept-Encoding", "gzip,deflate");

		String ContentEncoding = null;
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				if (entry.getKey().equalsIgnoreCase("Content-Encoding"))
					ContentEncoding = entry.getValue();
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		if (method == "POST") {
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (param != null && !param.equals("")) {
				OutputStream output = conn.getOutputStream();
				if (ContentEncoding != null) {
					if (ContentEncoding.indexOf("gzip") > 0) {
						output = new GZIPOutputStream(output);
					} else if (ContentEncoding.indexOf("deflate") > 0) {
						output = new DeflaterOutputStream(output);
					}
				}
				output.write(param.getBytes());
			}
		}
		// 建立实际的连接
		conn.connect();
		return conn;
	}


	/**
	 * 发送GET请求
	 *
	 * @param url
	 *            目的地址
	 * @param parameters
	 *            请求参数，Map类型。
	 * @return 远程响应结果
	 */
	public static String sendGet(String url, Map<String, String> parameters) {
		String result="";
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if(parameters.size()==1){
				for(String name:parameters.keySet()){
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8"));
				}
				params=sb.toString();
			}else{
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			String full_url = url + "?" + params;
//            System.out.println(full_url);
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(full_url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
//            Map<String, List<String>> headers = httpConn.getHeaderFields();
			// 遍历所有的响应头字段
//            for (String key : headers.keySet()) {
//                System.out.println(key + "\t：\t" + headers.get(key));
//            }
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result ;
	}

	/**
	 * 发送POST请求
	 *
	 * @param url
	 *            目的地址
	 * @param parameters
	 *            请求参数，Map类型。
	 * @return 远程响应结果
	 */
	public static String sendPost(String url, Map<String, String> parameters) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8"));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 设置POST方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.write(params);
//            System.out.println(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 *Author:Jack
	 *Time:2017年9月5日下午2:20:26
	 *@param url
	 *@param parameters
	 *@return
	 *Return:String
	 *Description:发送Post请求，并且发送的是Json格式的数据。
	 */
	public static String sendPostJson(String url, Map<String, String> parameters) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		String params = "";// 编码之后的参数
		try {
			if(parameters!=null && parameters.size()>0){
				params = FastJsonUtil.toJSONString(parameters);
			}
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 设置POST方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.write(params);
//	            System.out.println(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
