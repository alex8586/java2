<%@attribute name="aProduct" required="true" type="lv.javaguru.java2.domain.Product" %>
<div class="col l3 s12 m7">
    <div class="card horizontal">
        <div class="card-image">
            <img src="http://lorempixel.com/100/190/nature/10">
        </div>
        <div class="card-stacked">
            <div class="card-content">
                <span class="card-title"><%=aProduct.getDisplayName()%></span>
                <p><%=aProduct.getDisplayDescription()%>
                </p>
            </div>
            <div class="card-action">
                <a href="#">This is a link</a>
            </div>
            hi
        </div>
    </div>
</div>