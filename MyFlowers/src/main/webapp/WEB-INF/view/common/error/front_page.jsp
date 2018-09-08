<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src="/static/web/js/page.js"></script>
<c:if test="${page != null && page.totalRecords>0}">
    <div class="paging">
        <a href="javascript:goPage(1);" title="">首</a>
        <c:choose>
            <c:when test="${page.first}">
                <a id="backpage" class="undisable" href="javascript:void(0)" title="">&lt;</a>
            </c:when>
            <c:otherwise>
                <a id="backpage" href="javascript:goPage(${page.pageNo-1 });" title="">&lt;</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${page.last}">
                <a id="nextpage" href="javascript:void(0)" title="" class="undisable">&gt;</a>
            </c:when>
            <c:otherwise>
                <a id="nextpage" href="javascript:goPage(${page.pageNo+1});" title="">&gt;</a>
            </c:otherwise>
        </c:choose>
        <a href="javascript:goPage(${page.totalPageSize});" title="">末</a>
        <div class="clear"></div>
    </div>


</c:if>
<script type="text/javascript">
    var totalPageSize =${page.totalPageSize};
    var currentPage = ${page.pageNo};
    currentPage = (currentPage - 1) < 1 ? 1 : currentPage;
    var totalPage = ${page.totalPageSize};
    showPageNumber();
</script>