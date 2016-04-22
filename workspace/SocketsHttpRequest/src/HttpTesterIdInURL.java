

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated resources.
 */
public class HttpTesterIdInURL {
    private CloseableHttpClient httpclient;
	private String baseUrl;
	
	static int testLen = 1000;

    public HttpTesterIdInURL(String url) {
        httpclient = HttpClients.createDefault();
        baseUrl = url;
	}

	public final static void main(String[] args) throws Exception {
    	
        try
        {

        		ExecutorService executor = Executors.newFixedThreadPool(100);
	        	Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 0; id < 1*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.successPost(myId);
	        			}
	        		});
	        	}
	        	
	        	List<Future<Boolean>> futures = executor.invokeAll(callables);
	        	boolean result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	        		System.out.println("Success POST: passed!");
	        	else
	        		System.err.println("Success POST: failed!");        		


	        	
	        	
	        	
	        	
	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 0; id < 1*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.failPost(myId);
	        			}
	        		});        		
	        	}
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	        		System.out.println("Fail POST: passed!");
	        	else
	        		System.err.println("Fail POST: failed!");        		

	        	
	        	
	        	
	        	
	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 0; id < 1*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.successGet(myId);
	        			}
	        		});        		
	        	}
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
                	System.out.println("Success GET: passed!");
            	else
            		System.err.println("Success GET: failed!");


	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 1*testLen; id < 2*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.failGet(myId);
	        			}
	        		});        		
	        	}
	        	
	        	
	        	
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	            	System.out.println("Fail GET: passed!");
	        	else
	        		System.err.println("Fail GET: failed!");


	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 0; id < 1*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.successPut(myId);
	        			}
	        		});        		
	        	}
	        	
	        	
	        	
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	            	System.out.println("Success PUT: passed!");
	        	else
	        		System.err.println("Success PUT: failed!");

	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 1*testLen; id < 2*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.failPut(myId);
	        			}
	        		});        		
	        	}
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	            	System.out.println("Fail PUT: passed!");
	        	else
	        		System.err.println("Fail PUT: failed!");


	        	
	        	
	        	
	        	
	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 0; id < 1*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.successDelete(myId);
	        			}
	        		});        		
	        	}
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	            	System.out.println("Success DELETE: passed!");
	        	else
	        		System.err.println("Success DELTE: failed!");



	        	
	        	
	        	
	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 1*testLen; id < 2*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.failDelete(myId);
	        			}
	        		});        		
	        	}
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	            	System.out.println("Fail DELETE: passed!");
	        	else
	        		System.err.println("Fail DELETE: failed!");


	        	executor = Executors.newFixedThreadPool(100);
	        	callables = new HashSet<Callable<Boolean>>(); 
	        	for(long id = 5*testLen; id < 6*testLen; id++)
	        	{
	        		final HttpTesterIdInURL tester = new HttpTesterIdInURL(args[0] + ":" + args[1]+"/");
	        		final long myId = id;
	        		callables.add(new Callable<Boolean>(){
	        			public Boolean call() throws Exception {
	        				return tester.successLongPostGet(myId);
	        			}
	        		});        		
	        	}
	        	
	        	futures = executor.invokeAll(callables);
	        	result = true;
	        	for(Future<Boolean> f : futures) {
	        		result = result && f.get();
	        		
	        		if(!result)
	        			break;
	        	}
	        	
	        	if(result)
	            	System.out.println("Success long POST&GET: passed!");
	        	else
	        		System.err.println("Success long POST&GET: failed!");


        	
        } finally {
            
        }
        
    }

	private void close() throws IOException {
		httpclient.close();
	}

	private boolean successPost(long id) throws Exception {
		boolean res = false;
    	//Success POST
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(id))
		        .build();
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(new StringEntity("data="+Long.toHexString(id)));
        CloseableHttpResponse postResponse = httpclient.execute(postMethod);

        try {
            //System.out.println(postResponse.getStatusLine());
            if(postResponse.getStatusLine().getStatusCode() == 201 || 
        		postResponse.getStatusLine().getStatusCode() == 202)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }

            HttpEntity postEntity = postResponse.getEntity();
            EntityUtils.consume(postEntity);
        } finally {
            postResponse.close();
        }
        return res;
	}

	private boolean failPost(long myId) throws Exception
	{
		boolean res = false;
    	//Fail POST
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(new StringEntity("data="+Long.toHexString(myId)));
        CloseableHttpResponse postResponse = httpclient.execute(postMethod);

        try 
        {
        	int statusCode = postResponse.getStatusLine().getStatusCode();
        	
            if(statusCode == 400 || statusCode == 403)
            {
            	res = true;
            }
            else
            {
            	System.out.println("code " + statusCode + "  " + myId);
            	res = false;
            }

            HttpEntity postEntity = postResponse.getEntity();
            EntityUtils.consume(postEntity);
        } finally {
            postResponse.close();
        }
        return res;
	}
	
	private boolean successGet(long myId) throws Exception
	{
		boolean res = false;
		
		//Success GET
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();
        HttpGet getMethod = new HttpGet(uri);
        CloseableHttpResponse getResponse = httpclient.execute(getMethod);
        try {
            //System.out.println(getResponse.getStatusLine());
            HttpEntity getEntity = getResponse.getEntity();
            int statusCode = getResponse.getStatusLine().getStatusCode();
            String entityString = EntityUtils.toString(getEntity);
            if( (statusCode == 200 || statusCode == 302)&& 
            	entityString.equals(Long.toHexString(myId)))
            {
            	res = true;
            }
            else
            {
            	System.out.println(entityString);
            	res = false;
            }
            EntityUtils.consume(getEntity);
        } finally {
            getResponse.close();
        }

		return res;
	}
	
	private boolean failGet(long myId) throws Exception
	{
		boolean res = false;
		//Fail GET
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();

        HttpGet getMethod = new HttpGet(uri);
        CloseableHttpResponse getResponse = httpclient.execute(getMethod);
        try {
            //System.out.println(getResponse.getStatusLine());
            HttpEntity getEntity = getResponse.getEntity();
            int statusCode = getResponse.getStatusLine().getStatusCode();
            String entityString = EntityUtils.toString(getEntity);
            if(statusCode == 404 || statusCode == 400)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }
            EntityUtils.consume(getEntity);
        } finally {
            getResponse.close();
        }

		return res;
	}
	
	private boolean successPut(long myId) throws Exception
	{
		boolean res = false;
    	//Success PUT
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();

        HttpPut putMethod = new HttpPut(uri);
        putMethod.setEntity(new StringEntity("data="+Long.toHexString(-1*myId)));
        CloseableHttpResponse postResponse = httpclient.execute(putMethod);

        try {
            //System.out.println(postResponse.getStatusLine());
            if(postResponse.getStatusLine().getStatusCode() == 200 ||
               postResponse.getStatusLine().getStatusCode() == 204)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }

            HttpEntity postEntity = postResponse.getEntity();
            EntityUtils.consume(postEntity);
        } finally {
            postResponse.close();
        }

		return res;
	}
	
	private boolean failPut(long myId) throws Exception
	{
		boolean res = false;
    	//Fail PUT
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();

        HttpPut putMethod = new HttpPut(uri);
        putMethod.setEntity(new StringEntity("data="+Long.toHexString(myId)));
        CloseableHttpResponse postResponse = httpclient.execute(putMethod);

        try {
            //System.out.println(postResponse.getStatusLine());
            if(postResponse.getStatusLine().getStatusCode() == 404)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }

            HttpEntity postEntity = postResponse.getEntity();
            EntityUtils.consume(postEntity);
        } finally {
            postResponse.close();
        }

		return res;
	}
	
	private boolean successDelete(long myId) throws Exception
	{
		boolean res = false;
		//Success DELETE
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();

        HttpDelete delMethod = new HttpDelete(uri);
        CloseableHttpResponse getResponse = httpclient.execute(delMethod);
        try {
            //System.out.println(getResponse.getStatusLine());
            HttpEntity getEntity = getResponse.getEntity();
            if(getResponse.getStatusLine().getStatusCode() == 200 ||
        		getResponse.getStatusLine().getStatusCode() == 202 ||
        		getResponse.getStatusLine().getStatusCode() == 204)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }
            EntityUtils.consume(getEntity);
        } finally {
            getResponse.close();
        }

		return res;
	}
	
	private boolean failDelete(long myId) throws Exception
	{
		boolean res = false;
		//Fail DELETE
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();

        HttpDelete delMethod = new HttpDelete(uri);
        CloseableHttpResponse getResponse = httpclient.execute(delMethod);
        try {
            //System.out.println(getResponse.getStatusLine());
            HttpEntity getEntity = getResponse.getEntity();
            if(getResponse.getStatusLine().getStatusCode() == 404)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }
            EntityUtils.consume(getEntity);
        } finally {
            getResponse.close();
        }

		return res;
	}
	
	private boolean successLongPostGet(long myId) throws Exception {
		boolean res = false;
		String longString = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"+
							"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
    	//Success POST
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(baseUrl)
		        .setPath(Long.toString(myId))
		        .build();

        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(new StringEntity("data="+longString));
        CloseableHttpResponse postResponse = httpclient.execute(postMethod);

        try {
            //System.out.println(postResponse.getStatusLine());
        	int statusCode = postResponse.getStatusLine().getStatusCode();
            if(statusCode == 200 || statusCode == 201)
            {
            	res = true;
            }
            else
            {
            	res = false;
            }

            HttpEntity postEntity = postResponse.getEntity();
            EntityUtils.consume(postEntity);
        } finally {
            postResponse.close();
        }

        if(res)
        {
			//Success GET
	        HttpGet getMethod = new HttpGet(uri);
	        CloseableHttpResponse getResponse = httpclient.execute(getMethod);
	        try {
	            HttpEntity getEntity = getResponse.getEntity();
	            int statusCode = getResponse.getStatusLine().getStatusCode();
	            String entityString = EntityUtils.toString(getEntity);
	            if((statusCode == 200 || statusCode == 302)&& 
	            		entityString.equals(longString))
	            {
	            	res = true;
	            }
	            else
	            {
	            	System.out.println(entityString);
	            	res = false;
	            }
	            EntityUtils.consume(getEntity);
	        } finally {
	            getResponse.close();
	        }
        }
		return res;
	}
}
