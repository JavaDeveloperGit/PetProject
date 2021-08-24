package ua.com.vovacoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.vovacoffee.model.Category;
import ua.com.vovacoffee.model.Product;
import ua.com.vovacoffee.service.CategoryService;
import ua.com.vovacoffee.service.ProductService;

import java.util.List;

@Controller
public class SEOController {
    private final static String ROBOTS = "User-agent: Yandex\n" +
            "Disallow: /admin\n" +
            "Disallow: /manager\n" +
            "Disallow: /login\n" +
            "Disallow: /resources\n" +
            "Host: vovacoffee.com.ua\n" +
            "\n" +
            "User-agent: Googlebot\n" +
            "Disallow: /admin\n" +
            "Disallow: /manager\n" +
            "Disallow: /login\n" +
            "Disallow: /resources\n" +
            "\n" +
            "User-agent: *\n" +
            "Crawl-delay: 30\n" +
            "Disallow: /admin\n" +
            "Disallow: /manager\n" +
            "Disallow: /login\n" +
            "Disallow: /resources\n" +
            "\n" +
            "Sitemap: http://vovacoffee.com.ua/sitemap.xml";

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public SEOController(ProductService productService, CategoryService categoryService) {
        super();
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/robots.txt", produces = {"text/plain"})
    @ResponseBody
    public String getRobotsTxt() {
        return ROBOTS;
    }

    @RequestMapping(value = "/sitemap.xml", produces = {"application/xml"})
    @ResponseBody
    public String getSiteMapXml() {
        StringBuilder sitemap = new StringBuilder();
        sitemap.append("<urlset\n")
                .append("      xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n")
                .append("      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n")
                .append("      xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9\n")
                .append("            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\n")
                .append("<url>\n")
                .append("  <loc>http://vovacoffee.com.ua/</loc>\n")
                .append("</url>\n");

        // Ссылки на категории товаров.
        List<Category> categories = categoryService.getAll();
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://vovacoffee.com.ua/category_").append(category.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

        // Ссылки на товары.
        List<Product> products = productService.getAll();
        if (!products.isEmpty()) {
            sitemap.append("<url>\n")
                    .append("  <loc>http://vovacoffee.com.ua/all_products</loc>\n")
                    .append("</url>\n");

            for (Product product : products) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://vovacoffee.com.ua/product_").append(product.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

        sitemap.append("</urlset>");

        return sitemap.toString();
    }
}
