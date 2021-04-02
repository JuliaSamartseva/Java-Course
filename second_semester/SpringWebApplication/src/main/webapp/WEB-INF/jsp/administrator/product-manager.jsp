<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product addition form</title>
    <%@ include file="../../bootstrap-css.html" %>
    <script src="${pageContext.request.contextPath}/js/product-manager.js"></script>
</head>
<body>
<div class="container align-content-center">
    <br>
    <h3 class="text-center">Product manager form</h3>
    <hr>

    <div class="card bg-light mx-auto" style="max-width: 600px;">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Product manager</h4>
            <br>
            <form:form method="POST" modelAttribute="productForm">
                <div class="form-group">
                    <label for="product-name">Name</label>
                    <form:input
                            type="text"
                            name="product-name"
                            path="name"
                            id="product-name"
                            placeholder="Name"
                            class="form-control col"/>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <form:input
                            type="number"
                            name="price"
                            id="price"
                            placeholder="Price"
                            path="price"
                            class="form-control col"
                            min="1" max="500"/>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <form:input
                            type="text"
                            name="description"
                            id="description"
                            path="description"
                            placeholder="Description"
                            class="form-control col"/>
                </div>

                <div class="form-group">
                    <label for="type">
                        Please select the product type:
                    </label>
                    <form:select name="type" id="type" class="form-control w-auto" path="type.id">
                    </form:select>
                </div>

                <div class="form-group">
                    <button type="submit" id="product-submit" class="btn btn-primary col">
                        Save
                    </button>
                </div>
            </form:form>
        </article>
    </div>

    <div class="modal fade" id="failureModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger">Product addition failure</h5>
                </div>
                <div class="modal-body">
                    <p id="failureModalText"></p>
                </div>
                <div class="modal-footer" id="failureModalFooter">
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<%@ include file="../../bootstrap-js.html" %>
</html>