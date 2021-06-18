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

        <h1>Configuration du site de ${page.titre }</h1>
        <h2><span class="rotating">Vous êtes en mode Administrateur, Vous avez tout les droits, Vous êtes le Maître de la Cité de ${page.titre }</span></h2>
 <!--==========================
  Formulaire Section
  ============================-->
  			<form class="was-validated" action="${pageContext.request.contextPath }/Configuration" method="post">
  
        <div class="actions container">
  <div class="form-group">
    <label for="titreInput">Titre</label>
    <input type="text" class="form-control" id="titreInput" name="titre" placeholder="${page.titre }">
  </div>
  <div class="form-group">
    <label for="habitantInput">Habitants</label>
    <input type="number" min=0 class="form-control" id="habitantInput" name="nbHabitants" placeholder="${page.nbHabitants }">
  </div>
 <div class="mb-3">
    <label for="validationTextarea">Textarea</label>
    <textarea 
    	 <c:if test=""> class="form-control is-invalid"</c:if>
   class="form-control" id="validationTextarea" placeholder="Description" required></textarea>
    <div class="invalid-feedback">
      Please enter a message in the textarea.
    </div>
  </div>
  <div class="form-group">
    <label for="dateInput">Date du prochain conseil</label>
    <input type="date" class="form-control" id="dateInput" name="dateConseil" placeholder="${page.dateConseil }">
  </div>

          <input type="submit" name="enregistrer" value="Enregistrer"  class=" form-control btn btn-services">
        </div>
  </form>
      </div>
    </div>
  </section>

  <!--==========================
  Header Section
  ============================-->
  <header id="header">
    <div class="container">

      <div id="logo" class="pull-left">
        <a href="#hero"><img src="img/logo.png" alt="" title="" /></img></a>
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
