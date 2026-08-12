<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    if (session.getAttribute("idusuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestão Escolar - Cursos</title>

<style>

      * {
          margin: 0;
          padding: 0;
          box-sizing: border-box;
      }


      body {
          font-family: Arial, Helvetica, sans-serif;

          background: #f5f7fb;

          color: #1f2937;
      }


      /* =========================
         MENU
      ========================= */

      .topbar {

          height: 70px;

          background: #ffffff;

          border-bottom: 1px solid #e5e7eb;

          display: flex;

          align-items: center;

          padding: 0 35px;

          position: sticky;

          top: 0;

          z-index: 100;
      }


      .logo {

          font-size: 21px;

          font-weight: 700;

          color: #2563eb;

          margin-right: 50px;
      }


     .menu {
    display: flex;
    gap: 8px;
    height: 100%;

    /* Centraliza o menu no topo */
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
}

.menu a {
    text-decoration: none;

    color: #6b7280;

    font-size: 15px;

    font-weight: 600;

    padding: 0 20px;

    height: 100%;

    display: flex;

    align-items: center;

    position: relative;
}

.menu a:hover {
    color: #2563eb;
}

.menu a.active {
    color: #2563eb;
}

.menu a.active::after {
    content: "";

    position: absolute;

    bottom: 0;

    left: 20px;

    right: 20px;

    height: 3px;

    background: #2563eb;

    border-radius: 3px 3px 0 0;
}



.topbar {
    height: 70px;

    background: #ffffff;

    border-bottom: 1px solid #e5e7eb;

    display: flex;

    align-items: center;

    padding: 0 35px;

    position: sticky;

    top: 0;

    z-index: 100;
}


      /* =========================
         CONTAINER
      ========================= */

      .container {

          max-width: 1250px;

          margin: 0 auto;

          padding: 35px 25px 60px;
      }


      .page-header {

          display: flex;

          justify-content: space-between;

          align-items: center;

          margin-bottom: 28px;
      }


      .page-title h1 {

          font-size: 28px;

          margin-bottom: 6px;

          color: #111827;
      }


      .page-title p {

          color: #6b7280;

          font-size: 14px;
      }


      /* =========================
         BOTÕES
      ========================= */

      .btn {

          display: inline-flex;

          align-items: center;

          justify-content: center;

          text-decoration: none;

          border: none;

          cursor: pointer;

          border-radius: 8px;

          padding: 11px 17px;

          font-size: 14px;

          font-weight: 600;
      }


      .btn-primary {

          background: #2563eb;

          color: white;
      }


      .btn-primary:hover {

          background: #1d4ed8;
      }


      /* =========================
         CARDS
      ========================= */

      .card {

          background: #ffffff;

          border: 1px solid #e5e7eb;

          border-radius: 12px;

          box-shadow:
              0 2px 8px rgba(0,0,0,0.03);
      }


      /* =========================
         PESQUISA
      ========================= */

      .filters {

          padding: 20px;

          margin-bottom: 20px;
      }


      .filters-grid {

          display: grid;

          grid-template-columns:
              1fr 1fr auto;

          gap: 15px;

          align-items: end;
      }


      .form-group {

          display: flex;

          flex-direction: column;

          gap: 7px;
      }


      .form-group label {

          font-size: 13px;

          font-weight: 600;

          color: #374151;
      }


      input,
      select {

          width: 100%;

          height: 42px;

          border: 1px solid #d1d5db;

          border-radius: 7px;

          padding: 0 12px;

          outline: none;

          font-size: 14px;

          background: white;
      }


      input:focus,
      select:focus {

          border-color: #2563eb;
      }


      .btn-filter {

          height: 42px;

          background: #111827;

          color: white;
      }


      /* =========================
         TABELA
      ========================= */

      .table-card {

          overflow: hidden;
      }


      .table-header {

          padding: 18px 20px;

          border-bottom: 1px solid #e5e7eb;

          display: flex;

          justify-content: space-between;

          align-items: center;
      }


      .table-header h2 {

          font-size: 17px;
      }


      .total {

          font-size: 13px;

          color: #6b7280;
      }


      .table-wrapper {

          overflow-x: auto;
      }


      table {

          width: 100%;

          border-collapse: collapse;

          min-width: 850px;
      }


      th {

          background: #f9fafb;

          color: #6b7280;

          font-size: 12px;

          text-transform: uppercase;

          letter-spacing: 0.4px;

          text-align: left;

          padding: 14px 20px;

          border-bottom: 1px solid #e5e7eb;
      }


      td {

          padding: 16px 20px;

          border-bottom: 1px solid #f0f0f0;

          font-size: 14px;
      }


      tbody tr:hover {

          background: #fafafa;
      }


      /* =========================
         ALUNO
      ========================= */

      .student {

          display: flex;

          align-items: center;

          gap: 12px;
      }


      .avatar {

          width: 40px;

          height: 40px;

          border-radius: 50%;

          background: #dbeafe;

          color: #2563eb;

          display: flex;

          align-items: center;

          justify-content: center;

          font-weight: 700;
      }


      .student-name {

          font-weight: 600;

          color: #111827;
      }


      .student-id {

          color: #9ca3af;

          font-size: 12px;

          margin-top: 3px;
      }


      /* =========================
         STATUS
      ========================= */

      .status {

          display: inline-flex;

          align-items: center;

          gap: 6px;

          padding: 5px 10px;

          border-radius: 20px;

          font-size: 12px;

          font-weight: 600;
      }


      .status.active {

          background: #dcfce7;

          color: #15803d;
      }


      .status-dot {

          width: 6px;

          height: 6px;

          border-radius: 50%;

          background: currentColor;
      }


      /* =========================
         AÇÕES
      ========================= */

      .actions {

          display: flex;

          gap: 6px;
      }


      .action-btn {

          width: 34px;

          height: 34px;

          border: 1px solid #e5e7eb;

          background: white;

          border-radius: 7px;

          text-decoration: none;

          display: inline-flex;

          align-items: center;

          justify-content: center;

          font-size: 14px;
      }


      .action-btn:hover {

          background: #f3f4f6;
      }


      /* =========================
         MODAL SEM JAVASCRIPT
      ========================= */

      .modal-checkbox {

          display: none;
      }


      .modal {

          position: fixed;

          inset: 0;

          background: rgba(15, 23, 42, 0.55);

          display: flex;

          align-items: center;

          justify-content: center;

          padding: 20px;

          opacity: 0;

          visibility: hidden;

          pointer-events: none;

          transition: 0.2s;

          z-index: 500;
      }


      .modal-checkbox:checked + .modal {

          opacity: 1;

          visibility: visible;

          pointer-events: auto;
      }


      .modal-box {

          width: 100%;

          max-width: 650px;

          background: #ffffff;

          border-radius: 14px;

          box-shadow:
              0 25px 70px rgba(0,0,0,0.25);

          overflow: hidden;
      }


      /* =========================
         MODAL HEADER
      ========================= */

      .modal-header {

          padding: 20px 24px;

          border-bottom: 1px solid #e5e7eb;

          display: flex;

          align-items: center;

          justify-content: space-between;
      }


      .modal-header h2 {

          font-size: 19px;

          color: #111827;
      }


      .modal-close {

          width: 34px;

          height: 34px;

          border-radius: 7px;

          display: flex;

          align-items: center;

          justify-content: center;

          cursor: pointer;

          font-size: 22px;

          color: #6b7280;

          background: #f3f4f6;
      }


      .modal-close:hover {

          background: #e5e7eb;

          color: #111827;
      }


      /* =========================
         MODAL BODY
      ========================= */

      .modal-body {

          padding: 24px;
      }


      .form-grid {

          display: grid;

          grid-template-columns: 1fr 1fr;

          gap: 18px;
      }


      .form-full {

          grid-column: 1 / -1;
      }


      /* =========================
         MODAL FOOTER
      ========================= */

      .modal-footer {

          padding: 18px 24px;

          border-top: 1px solid #e5e7eb;

          display: flex;

          justify-content: flex-end;

          gap: 10px;
      }


      .btn-cancel {

          background: #f3f4f6;

          color: #374151;
      }


      .btn-cancel:hover {

          background: #e5e7eb;
      }


      /* =========================
         RESPONSIVO
      ========================= */

      @media (max-width: 800px) {

          .topbar {

              padding: 0 15px;
          }


          .logo {

              margin-right: 15px;

              font-size: 18px;
          }


          .menu a {

              padding: 0 10px;

              font-size: 13px;
          }


          .menu a.active::after {

              left: 10px;

              right: 10px;
          }


          .container {

              padding: 25px 15px;
          }


          .page-header {

              align-items: flex-start;

              gap: 15px;

              flex-direction: column;
          }


          .filters-grid {

              grid-template-columns: 1fr;
          }


          .form-grid {

              grid-template-columns: 1fr;
          }


          .form-full {

              grid-column: auto;
          }
      }

</style>
</head>
<body>

	<header class="topbar">
	    <nav class="menu">
	        <a href="AlunosController" > Alunos </a>	
	        <a href="cursos.jsp" class="active"> Cursos </a>
	        <a href="matriculas.jsp"> Matrículas </a>
	    </nav>
	</header>
	
	
	
</body>
</html>