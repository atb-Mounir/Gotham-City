<%@ include file="../fragments/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
  <div id="preloader"></div>

  <!--==========================
  Hero Section
  ============================-->
  <section id="hero">
    <div class="hero-container">
      <div class="wow fadeIn">
<!--         <div class="hero-logo"> -->
<!--           <img class="" src="img/logo.png" alt="Imperial"> -->
<!--         </div> -->

        <h1>Administration de ${page.titre }</h1>
        <c:if test="${empty listeCodesErreur }">
        	        <h2><span class="rotating">Veuillez entrer votre Login, et votre Mot de Passe, S'il vous plaît</span></h2>
        </c:if>
        <c:if test="${!empty listeCodesErreur }">
        		<strong>
        			 <h2><span class="rotating text-danger" >ERREUR SUR LOGIN OU PASSWORD, Entrez à nouveau votre Login, et votre Mot de Passe</span></h2>
        		</strong>
        		<ul>
        			<c:forEach var="code" items="${listeCOdesErreur }">
        				<li>${LecteurMessage.getMessageErreur(code) }</li>
        			</c:forEach>
        		</ul>
        	</div>
        </c:if>
        
        <div class="actions">
          <form class="form-inline" action="${pageContext.request.contextPath }/Connexion" method="post">
  <div class="form-group ">
    <label for="login">Login</label>
    <input type="text" min=8 max=12 name="login" class="form-control " id="loginInput" aria-describedby="emailHelp">
  </div>
  <div class="form-group mx-sm-3 mb-2">
    <label for="password">Password</label>
    <input type="password" name="password" class="form-control " id="exampleInputPassword1">
  </div>
          <button type="submit"  class="form-control btn btn-services">Connexion</button>
          </form>
        </div>
      </div>
    </div>
  </section>

  <!--==========================
  Header Section
  ============================-->
  <header id="header">
    <div class="container">

      <div id="logo" class="pull-left">
        <a href="#hero">${page.titre }</a>
        <!-- Uncomment below if you prefer to use a text image -->
        <!--<h1><a href="#hero">Header 1</a></h1>-->
      </div>

      <%@ include file="../fragments/nav.jsp" %>
      <!-- #nav-menu-container -->
    </div>
  </header>
  <!-- #header -->


  <!--==========================
  Footer
============================-->

	
		
		
<%@ include file="../fragments/footer.jsp" %>
</body>

</html>
