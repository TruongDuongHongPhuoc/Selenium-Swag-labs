
```markdown
# SauceDemo Test Cases

code Source > Test

## Testing Web URL
[https://www.saucedemo.com/v1/index.html](https://www.saucedemo.com/v1/index.html)

## Test Cases

### Login
- Valid information
- Invalid username
- Invalid password
- Blanks
- Empty username
- Empty password

### Product Page
- Verify product list is displayed
- Verify sorting feature:
  - Price: Low to High
  - Price: High to Low
  - Product name: A → Z
  - Product name: Z → A
- Add product to cart
- Verify product image is displayed
- Verify opening left navigation with burger icon
- Verify closing left navigation:
  - With "X" icon
  - By clicking outside of the navigation
- Verify redirecting to cart by clicking the cart icon

### Cart Page
- Verify cart updates correctly (added products from homepage should be displayed)
- Verify removing items from cart
- Verify checkout process

### Checkout Step 1
- Verify continuing with valid checkout input
- Verify failure to continue with invalid checkout input (5 test cases should fail)

### Checkout Step 2
- Verify cancel button
- Verify products match cart items
- Verify finishing checkout
- Verify total price (all product prices + tax)

### Product Details
- Verify image is loaded
- Verify correct product name is displayed
- Verify "Add to Cart" button functionality
```

You can copy and paste this into your README file on GitHub. Let me know if you'd like any further adjustments or enhancements! 🚀
