<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="facade.Facade, org.json.JSONObject, org.json.JSONArray"
%>
<%
	Facade facade = new Facade(request);
	JSONArray initialProducts = facade.getInitialProducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=facade.getTitle()%></title>
<link rel="icon" type="image/svg" href=<%=facade.getLogoPath()%>>
<link rel="stylesheet" href=<%=facade.getStylePath()%>>
<link rel="stylesheet" href="WebContent/css/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="WebContent/font-awesome/css/fontawesome.css">
<link rel="stylesheet" href="WebContent/font-awesome/css/solid.css">
</head>
<body>
	<div class="navbar">
		<div class="dropdown">
			<button class="dropbtn"><i id="center" class="fa-solid fa-bars fa-2x"></i></button>
			<div class="dropdown-content">
				<a href="#type1">Type 1</a>
				<a href="#type2">Type 2</a>
				<a href="#type3">Type 3</a>
				<a href="sessionInfo.jsp">Session Info</a>
			</div>
		</div>
		<a id="site-logo" href="index.jsp" title="Home"><img id="center" src=<%=facade.getLogoPath(true)%>></a>
		<a id="site-title" href="index.jsp" title="Home"><%=facade.getTitle()%> Shop</a>
		<div class="right-container">
			<a href="#basket" title="Basket"><i id="center" class="fa-solid fa-basket-shopping"></i></a>
			<a href="login.jsp" title="Login"><i id="center" class="fa-solid fa-user"></i></a>
			<div class="search-container">
				<form action="index.jsp">
					<input type="text" placeholder="Search..." name="search">
				</form>
			</div>
		</div>
	</div>

	<div class="product-grid">
		<div class="w3-row">

			<%
				// grid with 6 columns
				// outer loop for columns
				for (int i = 0; i < 6; i++) {
			%>
			<div class="w3-col l2 m3 s6">
			<%
					// inner loop for rows
					for (int j = 0; i + j < initialProducts.length(); j += 6) {
						
						// product index on JSONArray
						int index = i + j;
						
						JSONObject product = initialProducts.getJSONObject(index);
						
						String imgSrcPath = "\"WebContent/img/products/" + //$NON-NLS-1$
										product.getString("productImageCd") + //$NON-NLS-1$
										"-270.png\""; //$NON-NLS-1$
						String priceVl = String.format("%.2f", Float.valueOf(product.getFloat("priceVl"))); //$NON-NLS-1$ //$NON-NLS-2$
						String previousPriceVl = String.format("%.2f", Float.valueOf(product.getFloat("previousPriceVl"))); //$NON-NLS-1$ //$NON-NLS-2$
			%>
				<div id="product-container" class="w3-container">
					<div class="w3-display-container">
						<div class="w3-card w3-hover-shadow" style="width:100%">
							<img src=<%=imgSrcPath%> alt="Product" style="width:100%">
							<%
								switch (product.get("priceSt").toString()) { //$NON-NLS-1$
									case "NEW": //$NON-NLS-1$
							%>
							<span id="new-product" class="w3-tag w3-display-topleft" style="display:block">New</span>
							<%
										break;
									case "SAL": //$NON-NLS-1$
							%>
							<span id="on-sale" class="w3-tag w3-display-topleft" style="display:block">On Sale</span>
							<%							
										break;
									case "REG": //$NON-NLS-1$
									default:
										break;
								}
							%>
							<form>
								<input type="checkbox" class="w3-check w3-display-topright">
							</form>
          					<%
          						if (product.get("priceSt").toString() == "SAL") { //$NON-NLS-1$ //$NON-NLS-2$
          					%>
          					<p class="price" style="display:none"></p>
          					<p class="price-old" style="display:block">€ <%=previousPriceVl%></p>
          					<p class="price-sale" style="display:block">€ <%=priceVl%></p>
          					<%
          						// end if
          						} else {
          					%>
          					<p class="price" style="display:block">€ <%=priceVl%></p>
          					<p class="price-sale" style="display:none"></p>
							<p class="price-old" style="display:none"></p>
          					<%	// end else	
          						}
          					%>
							<a id="view-more" href="#viewMore"><button id="view-more" class="w3-button w3-block">View More</button></a>
						</div>
					</div>
				</div>
			<%
					// end of inner loop
					}
			%>
			</div>
			<%
				// end of outer loop
				}
			%>
		</div>
	</div>
</body>
</html>
