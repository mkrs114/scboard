<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물리스트"/>

<%@ include file="../common/head.jspf" %>

<section class="mt-5">
    <div class="container mx-auto px-3">
        <div class="table-box-type-1">
            <table border="1">
                <colgroup>
                        <col width="200"/>
                        <col width="800"/>
                </colgroup>
                <tbody>
                        <tr>
                            <th>번호</th>
                            <td>${article.id}</td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td>${article.title}
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>${article.content}
                            </td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td>${article.writer}</td>
                        </tr>
                        <tr>
                            <th>작성일</th>
                            <td>${article.regDate.substring(2,16)}</td>
                        </tr>
                        <tr>
                            <th>수정일</th>
                            <td>${article.modDate.substring(2,16)}</td>
                        </tr>
                </tbody>
            </table>
        </div>
        <div class="btns">
            <button type="button" onclick="history.back();">뒤로가기</button>
        </div>
        </div>
</section>    
<%@ include file="../common/foot.jspf" %>
    