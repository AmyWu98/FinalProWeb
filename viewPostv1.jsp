<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">
<sql:setDataSource
		driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost/eeit58group3"
		user="root"
		password="root"
		/>	
		<sql:query var="rs">
        SELECT * FROM activity
        </sql:query>
<head>
    <meta charset="UTF-8">
    <title>報團頁面</title>
    <!-- 響應式網站 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Boostrap 導入程式 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
        <!-- 中文字型樣式 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+HK:wght@500&family=Noto+Sans+JP:wght@700&family=Noto+Sans+TC:wght@900&display=swap"
        rel="stylesheet">
    <!-- 連結CSS外部檔案 -->
    <link rel="stylesheet" href="./css/joinTeam.css">
    <link rel="stylesheet" href="./css/navbar&footer.css">
    <!-- MDB頭像 -->
    <script type="text/javascript" src="./Js/mdb.min.js"></script>
</head>

<body>
	
     <!-- 導覽列 -->
    <header class="main-header">
        <div class="container">
            <a href="" class="Logo">
                <img src="./Images/logo.png" alt="Logo" width="30" height="36">
                <h2>Join</h2>
            </a>
            <!-- 導覽列文字 -->
            <nav class="navbar-text">
                <a href="">最新消息</a>
                <a href="">附近場館</a>
                <a href="">發起揪團</a>
                <a href="">我的活動</a>
            </nav>
            <nav class="navbar navbar-expand-lg">
                <div class="container-fluid">
                    <ul class="navbar-nav">
                        <!-- Avatar頭像 -->
                        <li class="nav-item Avatar dropdown">
                            <a class="nav-link dropdown-toggle d-flex align-items-center" href="#"
                                id="navbarDropdownMenuLink" role="button" data-mdb-toggle="dropdown"
                                aria-expanded="false">
                                <img src="./Images/user.png" class="rounded-circle" width="50" height="50"
                                    alt="Portrait of a Woman" loading="lazy" />
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li>
                                    <a class="dropdown-item" href="#">登入</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">個人資訊</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <!-- 選擇器 -->
    <div class="container">
        <div class="d-flex flex-row bd-highlight mb-3">
            <select class="form-select se2" aria-label="Default select example">
                <option selected>地點</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
            <select class="form-select" aria-label="Default select example">
                <option selected>時間</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
            <select class="form-select" aria-label="Default select example">
                <option selected>團名</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
            <select class="form-select" aria-label="Default select example">
                <option selected>發起人</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
            <button type="button" class="btn btn-outline-dark">放大鏡</button>
            <button type="button" class="btn btn2 btn-outline-dark">看更多</button>
        </div>
    </div>
    

        <!-- card -->
        <!-- 依活動截止時間最近 -->
        <c:forEach items="${rs.rows}" var="activity">
        <div class="card" style="width: 18rem;">
            <img src="./Images/badminton.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${activity.activityTitle}</h5>
                <p class="card-text">${activity.description}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">${activity.activityTime}</li>
                <li class="list-group-item">${activity.location.substring(0,3)}</li>
                <li class="list-group-item">${activity.level}</li>
            </ul>
            <div class="card-body">
                <a href="#" class="card-link">${activity.host}</a>
                <a href="#" class="card-link">${activity.location}</a>
            </div>
        </div>
    </c:forEach>
</body>

</html>
