<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }

        #nc {
            /* width:20em;*//*保证文字不会被半汉字截断,显示10个文字*/
            overflow: hidden; /*超出长度的文字隐藏*/
            text-overflow: ellipsis; /*文字隐藏以后添加省略号*/
            white-space: nowrap; /*强制不换行*/

        }
    </style>

</head>
<body>

<div style="background-color: #fcfffa">
    <jsp:include page="header.jsp"/>
    <div class="container" style="margin-top: 100px;">
        <div class="row">
            <div id="demo" class="col-sm-8 carousel slide" data-ride="carousel">
                <!-- 指示符 -->
                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                    <li data-target="#demo" data-slide-to="4"></li>
                    <li data-target="#demo" data-slide-to="5"></li>
                </ul>
                <!-- 轮播图片 -->
                <div class="carousel-inner">

                    <div class="carousel-item active">
                        <img src="http://static.runoob.com/images/mix/img_fjords_wide.jpg">
                        <div class="carousel-caption">
                            <h3>欢迎使用本系统</h3>
                        </div>
                    </div>
                    <c:if test='${indexinfo.news.get("new2")!=null}'>
                        <c:forEach items='${indexinfo.news.get("new2")}' var="news">
                            <div class="carousel-item">
                                <a href="/new/detail?n_id=${news.n_id}"> <img src="${news.imgurl}"
                                                                              style="height:255px;width: 800px"></a>
                                <div class="carousel-caption">
                                    <h3>${news.title}</h3>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <%--
                                    <div class="carousel-item">
                                        <img src="http://static.runoob.com/images/mix/img_nature_wide.jpg">
                                        <div class="carousel-caption">
                                            <h3>第二张图片描述标题</h3>
                                            <p>描述文字!</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img src="http://static.runoob.com/images/mix/img_mountains_wide.jpg">
                                        <div class="carousel-caption">
                                            <h3>第三张图片描述标题</h3>
                                            <p>描述文字!</p>
                                        </div>
                                    </div>--%>
                </div>
                <!-- 左右切换按钮 -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>
            <div class="col-sm-4">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">热点技术</a>
                    </li>
                </ul>
                <div class="list-group">
                    <c:if test='${indexinfo.news.get("new1")!=null}'>
                        <c:forEach items='${indexinfo.news.get("new1")}' var="news">
                            <a href="/new/detail?n_id=${news.n_id}"
                               class="list-group-item list-group-item-action">${news.title}</a>

                        </c:forEach>
                    </c:if>
                    <c:if test='${indexinfo.news.get("new1")==null}'>
                        暂无内容！
                    </c:if>
                </div>
            </div>
        </div>

        <br>


        <div class="row">
            <div class="col-sm-8">
                <h4>热门新闻</h4>
                <div class="list-group ">
                    <c:if test='${indexinfo.news.get("new2")!=null}'>
                        <c:forEach items='${indexinfo.news.get("new2")}' var="news">

                            <a href="/new/detail?n_id=${news.n_id}"
                               class="list-group-item list-group-item-action">${news.title}</a>

                        </c:forEach>
                    </c:if>
                    <c:if test='${indexinfo.news.get("new2")==null}'>
                        暂无内容！
                    </c:if>

                </div>
            </div>
            <div class="col-sm-4 ">
                <h4>公告</h4>

                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Card title</h4>
                        <c:if test="${indexinfo.notics!=null}">
                            <c:forEach items="${indexinfo.notics}" var="notice">
                                <p id="nc"><a href="#">${notice.content}</a></p>
                            </c:forEach>
                        </c:if>
                        <c:if test="${indexinfo.notics==null}">
                            <p id="nc">暂无公共！</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>