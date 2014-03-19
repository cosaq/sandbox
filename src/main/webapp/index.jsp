<html>
<head>
    //<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
  
     <script>
		function createProduct()
		{
			
			var xmlhttp;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
				alert("POST readyState=" + xmlhttp.readyState + " status=" + xmlhttp.status);
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	alert("P0ST returned" + xmlhttp.responseText);
			    }
			  }
			xmlhttp.open("POST","webapi/product",true);
			//Send the proper header information along with the request
			
			var data = '{"id":"","name":"Produkt Z"}';

			alert("POST:ing " + data);

			xmlhttp.setRequestHeader("Content-type", "application/json");
			xmlhttp.setRequestHeader("Content-length", data.length);
			xmlhttp.send(data);
		}
	</script>
  
  
     
	<script>
		function loadProductsJSON()
		{
		var xmlhttp;
		var txt,x,i;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	//'{"id":1,"name":"Produkt A","partList":{"Part":[{"id":101,"name":"PART a"},{"id":102,"name":"Part b"},{"id":103,"name":"Part c"}]}}'
				var jsonResponse = JSON.parse(xmlhttp.responseText);
				alert(xmlhttp.responseText);
				txt="";
				
			    for (i=0;i<jsonResponse.Product.length;i++)
			    {
					txt=txt + jsonResponse.Product[i].id + "&nbsp;";
					txt=txt + jsonResponse.Product[i].name + "<br>";
				}
		    	document.getElementById("getProducts").innerHTML=txt;
		    	document.getElementById("getProductsSrc").innerHTML=xmlhttp.responseText;
		    }
		  }
		xmlhttp.open("GET","webapi/product",true);
		xmlhttp.setRequestHeader("Accept","application/json");
		xmlhttp.send();
		}
	</script>
     
     
     <script>
		function loadProducts()
		{
		var xmlhttp;
		var txt,x,i;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    xmlDoc=xmlhttp.responseXML;
		    txt="";
		    x=xmlDoc.getElementsByTagName("Product");
		    for (i=0;i<x.length;i++)
		      {
		      txt=txt + (x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue) + "&nbsp;";
		      txt=txt + (x[i].getElementsByTagName("name")[0].childNodes[0].nodeValue) + "<br>";
		      }
		    document.getElementById("getXmlProduct").innerHTML=txt;
		    document.getElementById("getXmlProductsSrc").innerHTML=xmlhttp.responseText;
		    }
		  }
		xmlhttp.open("GET","webapi/product",true);
		xmlhttp.setRequestHeader("Accept","application/xml");
		xmlhttp.send();
		}
	</script>

   <script>
		function updateProduct()
		{
			
			var xmlhttp;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==204)
			    {
			    	alert("PUT called");
			    }
			  }
			xmlhttp.open("PUT","webapi/product/1",true);
			// xmlhttp.setRequestHeader("Accept","application/json");
			//Send the proper header information along with the request
			
			var data = '{"id":1,"name":"Produkt A","partList":{"Part":[{"id":101,"name":"PART a"},{"id":102,"name":"Part b"},{"id":103,"name":"Part c"}]}}';

			alert("PUT:ing " + data);

			xmlhttp.setRequestHeader("Content-type", "application/json");
			xmlhttp.setRequestHeader("Content-length", data.length);
			xmlhttp.send(data);
		}
	</script>

    <script>
		function loadProductXML()
		{
			var xmlhttp;
			var txt,x,i;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	alert("get XML '" + xmlhttp.responseText + "'");
			    
				    xmlDoc=xmlhttp.responseXML;
				    txt="";
				    x=xmlDoc.getElementsByTagName("Product");
				    for (i=0;i<x.length;i++)
				      {
				      txt=txt + (x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue) + "&nbsp;";
				      txt=txt + (x[i].getElementsByTagName("name")[0].childNodes[0].nodeValue) + "<br>";
				      }
				    document.getElementById("xmlProduct").innerHTML=txt;
			    	document.getElementById("xmlProductSrc").innerHTML=xmlhttp.responseText;
			    }
			  }
			xmlhttp.open("GET","webapi/product/1",true);
			xmlhttp.setRequestHeader("Accept","application/xml");
			xmlhttp.send();
		}
	</script>


    <script>
		function loadProductJSON()
		{
			var xmlhttp;
			var txt,x,i;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	//'{"id":1,"name":"Produkt A","partList":{"Part":[{"id":101,"name":"PART a"},{"id":102,"name":"Part b"},{"id":103,"name":"Part c"}]}}'
					var jsonResponse = JSON.parse(xmlhttp.responseText);
					alert(xmlhttp.responseText);
					txt="";
					txt=txt + jsonResponse.id + "&nbsp;";
					txt=txt + jsonResponse.name + "<br>";
					
				    for (i=0;i<jsonResponse.partList.length;i++)
				    {
						txt=txt + jsonResponse.partList.Part[i].id + "&nbsp;";
						txt=txt + jsonResponse.partList.Part[i].name + "<br>";
					}
			    	document.getElementById("getProduct").innerHTML=txt;
			    	document.getElementById("getProductSrc").innerHTML=xmlhttp.responseText;
			    }
			  }
			xmlhttp.open("GET","webapi/product/1",true);
			xmlhttp.setRequestHeader("Accept","application/json");
			xmlhttp.send();
		}
	</script>
      

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Simple Product Webapp</title>
</head>
<body>
    <h2>Simple Product RESTful Web Application!</h2>
    <p><a href="webapi/product">Lista alla produkter</a>
    <p><a href="webapi/product/1/">Visa produkt 1</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <br>
    <%
	  out.println("Your IP address is " + request.getRemoteAddr());
	%>
	<br>
	input:<input id="ip" type="text" name="" value="" /><br></br>
   	output:<input id="op" type="text" name="" value="" /><br></br>
    // <input type="button" value="Call Servlet" name="Call Servlet" id="call"/>

	var userInput = document.getElementById("ip").value;

	<button type="button" onclick="loadProducts()">Get products XML</button>
	<button type="button" onclick="loadProductsJSON()">Get products JSON</button>
	<button type="button" onclick="loadProductJSON()">Get product as JSON</button>
	<button type="button" onclick="loadProductXML()">Get product as XML</button>
	<button type="button" onclick="updateProduct()">PUT product </button>
	<button type="button" onclick="createProduct()">POST product </button>
 
     <br>
    <h3>GET products XML</h3>
     <div id="getXmlProduct"></div>
   <br>
    <div id="getXmlProductsSrc"></div>
     <br>
     
    <h3>GET products JSON</h3>
    <div id="getProducts">From JSON=</div>
    <br>
    <div id="getProductsSrc"></div>
 
    <br>
    <h3>GET product JSON</h3>
    <div id="getProduct">From JSON=</div>
    <br>
    <div id="getProductSrc"></div>
    
    <br>
    <h3>GET product XML</h3>
    <div id="xmlProduct">From JSON=</div>
    <br>
    <div id="xmlProductSrc"></div>
    
</body>
</html>
