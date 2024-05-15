package com.android.learningapp;
// Roadmaps.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roadmaps {
    private static Map<String, List<Object>> roadmapData = new HashMap<>();

    static {
        // Sample data for career roadmaps
        List<Object> softwareEngineerData = new ArrayList<>();
        softwareEngineerData.add(R.mipmap.se_foreground); // Image resource id
        softwareEngineerData.add("Regardless of where they end up, all software engineers start as" +
                " an individual contributor (IC). The IC track is where software engineers hone and" +
                "develop their technical skills. It’s also where they will develop the proficiencies " +
                "that will open the doors to new career paths and opportunities. Here are some of the" +
                " common levels on the IC career track: Junior Engineer Junior engineers perform a wide " +
                "range of entry-level operations, such as writing and maintaining code, debugging " +
                "software, and helping to design software applications. Junior engineers typically" +
                " start on a team and report to a more senior engineer, such as a team or manager. " +
                "In the United States, the average salary for a junior software engineer is around" +
                " $100,915 per year, which includes additional pay estimates, such as bonuses and " +
                "commissions. A typical junior engineer holds a degree in software engineering or " +
                "a related field. In lieu of a degree, some companies will consider graduates of a" +
                " software engineering bootcamp and/or self-taught candidates with strong coding " +
                "skills. Engineer Engineers are typically more proficient coders than junior engineers " +
                "and are familiar with more programming languages. They also have a greater understanding " +
                "of the software development process and tend to play a greater role in the various stage" +
                "s of the software development life cycle. The base pay for an engineer is $105,631 per year," +
                " with a total pay estimate of $120,435 per year.");
        softwareEngineerData.add(new String[]{
                "What are the typical educational requirements for becoming a software engineer?",
                "What programming languages are most essential for a software engineer to learn?",
                "How important is gaining practical experience through internships or projects for aspiring software engineers?",
                "What are some common career trajectories for software engineers, such as becoming a team lead or software architect?",
                "How does the demand for software engineers vary across different industries?",
                "What are the key skills or competencies that employers look for when hiring software engineers?",
                "How can networking and professional connections impact a software engineer's career growth?",
                "What are some popular certifications or qualifications that can enhance a software engineer's resume?",
                "How does the role of a software engineer evolve as one gains more experience in the field?",
                "What are the typical salary ranges for software engineers at different career stages and in different geographic locations?"
        }); // FAQs

        softwareEngineerData.add(new String[]{
                "Typical educational requirements for becoming a software engineer include a bachelor's degree in computer science, software engineering, or a related field.",
                "Some essential programming languages for software engineers to learn include Java, Python, C++, JavaScript, and SQL.",
                "Gaining practical experience through internships or projects is highly important for aspiring software engineers as it provides hands-on experience and helps in building a strong portfolio.",
                "Common career trajectories for software engineers include progressing from junior developer to senior developer, then to roles such as team lead, software architect, or project manager.",
                "The demand for software engineers varies across industries, with sectors like technology, finance, healthcare, and e-commerce often having high demand.",
                "Key skills and competencies that employers look for in software engineers include proficiency in programming languages, problem-solving abilities, teamwork, communication skills, and adaptability.",
                "Networking and professional connections can have a significant impact on a software engineer's career growth by providing access to job opportunities, mentorship, and industry insights.",
                "Popular certifications or qualifications that can enhance a software engineer's resume include certifications from organizations like Microsoft, Cisco, Oracle, or professional organizations like IEEE.",
                "The role of a software engineer evolves as one gains more experience, with opportunities to take on more complex projects, mentor junior developers, and contribute to architectural decisions.",
                "Salary ranges for software engineers vary depending on factors like experience, location, industry, and company size. Entry-level software engineers may earn around $60,000 to $80,000 annually, while experienced professionals can earn six-figure salaries or higher."
        }); // Answers
        roadmapData.put("Software Engineer", softwareEngineerData);


        List<Object> DsEngineerData = new ArrayList<>();
        DsEngineerData.add(R.mipmap.ds_foreground);
        DsEngineerData.add("Data scientists are analytical experts who utilize their skills in mathematics, statistics, and programming to extract insights and knowledge from large datasets. They work on various projects, including data modeling, machine learning, and predictive analytics, to help organizations make data-driven decisions. Data scientists collaborate with business stakeholders to understand their objectives, gather relevant data, and develop analytical solutions to solve complex problems. In the United States, the average salary for a data scientist is approximately $122,000 per year, with opportunities for additional compensation through bonuses, equity, and other benefits.");
        // Image resource id
        DsEngineerData.add(new String[]{
                "What are the typical educational requirements for becoming a Data Scientist?",
                "What programming languages are essential for Data Scientists to learn?",
                "How can practical experience benefit aspiring Data Scientists?",
                "What are the common career trajectories for Data Scientists?",
                "In which industries is there a high demand for Data Scientists?",
                "What key skills and competencies are sought after in Data Scientists?",
                "How can networking and professional connections impact a Data Scientist's career?",
                "Are there any popular certifications or qualifications that can enhance a Data Scientist's resume?",
                "How does the role of a Data Scientist evolve with experience?",
                "What salary ranges can Data Scientists expect based on experience and industry?",
                "What are some common tools and technologies used by Data Scientists in their work?",
                "What are the key challenges faced by Data Scientists in their profession?",
                "What are some emerging trends or areas of growth in the field of Data Science?",
                "How important is domain knowledge in Data Science, and how can it be acquired?",
                "What are some ethical considerations or challenges in Data Science projects?",
                "How can Data Scientists stay updated with the latest advancements and technologies in the field?",
                "What are the potential career paths for Data Scientists beyond traditional roles?",
                "What are some strategies for Data Scientists to effectively communicate their findings to non-technical stakeholders?",
                "How does the job market for Data Scientists vary across different regions or countries?",
                "What are some key differences between Data Science roles in industry and academia?",
                "What are the opportunities for specialization or niche areas within Data Science?",
                "How do Data Scientists typically collaborate with other professionals, such as software engineers or business analysts?",
                "What are the key considerations for Data Scientists when evaluating job offers or career opportunities?",
                "What are some recommended resources or communities for aspiring Data Scientists to learn and grow?",
                "What are the potential impacts of automation and AI on the future of Data Science careers?",
                "How can Data Scientists contribute to social good or address societal challenges through their work?",
                "What are the expectations for continuous learning and professional development in the field of Data Science?",
                "What are the career prospects for Data Scientists in the coming years, and how can individuals prepare for future trends?"
        });

        DsEngineerData.add(new String[]{
                "Typically, Data Scientists have a background in fields such as computer science, statistics, mathematics, or related disciplines. Advanced degrees (e.g., master's or Ph.D.) are common, but relevant experience and practical skills are also highly valued in the field.",
                "Data Scientists often need proficiency in programming languages such as Python, R, and SQL. Additionally, familiarity with tools and libraries like TensorFlow, PyTorch, and scikit-learn is beneficial.",
                "Hands-on experience with real-world data projects can provide invaluable insights and skills that are difficult to obtain through academic study alone. Participating in internships, personal projects, or Kaggle competitions can be beneficial.",
                "Data Scientists can follow various career paths, including becoming specialized in a particular industry or domain, transitioning to leadership roles (e.g., Data Science manager or director), or pursuing entrepreneurship.",
                "Industries such as technology, finance, healthcare, e-commerce, and marketing are known to have a high demand for Data Scientists due to the increasing importance of data-driven decision-making.",
                "Key skills for Data Scientists include proficiency in programming and statistical analysis, data manipulation and cleaning, machine learning techniques, data visualization, problem-solving, and critical thinking.",
                "Networking with professionals in the field, attending conferences or meetups, and engaging in online communities can provide opportunities for learning, collaboration, and career advancement.",
                "Certifications like Certified Analytics Professional (CAP), AWS Certified Machine Learning - Specialty, or Google Cloud Professional Data Engineer can demonstrate expertise and enhance job prospects.",
                "With experience, Data Scientists may take on more strategic or managerial roles, lead larger projects or teams, and contribute to shaping the organization's data strategy and vision.",
                "Salaries for Data Scientists vary based on factors such as experience, location, industry, and specific skills. Entry-level positions may start around $80,000-$100,000 per year, while senior roles can exceed $150,000 per year or more.",
                "Data Scientists use a wide range of tools and technologies, including programming languages (Python, R), frameworks (TensorFlow, PyTorch), databases (SQL, NoSQL), visualization libraries (Matplotlib, Seaborn), and cloud platforms (AWS, Google Cloud).",
                "Challenges in Data Science projects may include data quality issues, lack of domain expertise, ethical considerations, interpretability of machine learning models, and deployment of solutions into production.",
                "Emerging trends in Data Science include the adoption of automated machine learning (AutoML), the integration of AI and machine learning into business processes, increased focus on responsible AI, and the rise of edge computing and IoT analytics.",
                "Domain knowledge, especially in the context of the industry or problem domain, can greatly enhance a Data Scientist's ability to understand data, derive meaningful insights, and create actionable recommendations.",
                "Ethical considerations in Data Science encompass issues such as privacy, bias in algorithms and datasets, transparency of models, and the societal impact of AI-driven decisions.",
                "Data Scientists can stay updated by attending conferences, participating in online courses or webinars, reading research papers and blogs, and actively engaging with the Data Science community on platforms like GitHub and Stack Overflow.",
                "Career paths for Data Scientists may include transitioning to leadership roles (e.g., Chief Data Officer), specializing in niche areas (e.g., healthcare analytics, financial modeling), or branching into related fields such as data engineering or AI research.",
                "Effective communication involves translating technical findings into layman's terms, using data visualization to convey insights, understanding the needs of non-technical stakeholders, and tailoring presentations to the audience.",
                "Job markets for Data Scientists vary globally, with hotspots in tech hubs like Silicon Valley, New York, London, and Bangalore. However, remote work opportunities and distributed teams are becoming increasingly common.",
                "Academic roles in Data Science often involve research, teaching, and collaboration with other academics, while industry roles focus more on applied research, product development, and solving real-world problems.",
                "Specializations within Data Science can include areas like natural language processing (NLP), computer vision, deep learning, big data analytics, time series forecasting, and causal inference.",
                "Collaboration between Data Scientists and other professionals is essential for successful projects. This may involve working closely with software engineers, domain experts, business analysts, and project managers.",
                "When evaluating job offers, Data Scientists should consider factors such as salary and benefits, opportunities for growth and learning, the company culture and values, the scope and impact of the role, and work-life balance.",
                "Online courses (e.g., Coursera, edX), tutorials, and textbooks can provide structured learning paths. Engaging with online communities like Kaggle, Towards Data Science, and LinkedIn groups can offer support and networking opportunities.",
                "Automation and AI are expected to impact Data Science careers by automating routine tasks (e.g., data cleaning, model selection), creating new job roles (e.g., AI ethicists, ML ops engineers), and driving innovation across industries.",
                "Data Scientists can contribute to social good by using their skills for projects related to healthcare, climate change, education, poverty alleviation, disaster response, and humanitarian aid.",
                "Continuous learning is essential in Data Science due to the rapid pace of technological advancements. Data Scientists can stay updated through continuous education, attending workshops, and engaging in research and development activities.",
                "The demand for Data Scientists is projected to grow as organizations increasingly rely on data-driven insights to gain a competitive edge. Individuals can prepare by honing their skills, staying adaptable, and embracing lifelong learning."
        }); // Answers
        roadmapData.put("Data Scientist", DsEngineerData);

        List<Object> productManagerData = new ArrayList<>();
        productManagerData.add(R.mipmap.pm_foreground); // Image resource id
        productManagerData.add("Product managers play a crucial role in the development and success of a product. They are responsible for defining the product vision, strategy, and roadmap, as well as prioritizing features and enhancements based on customer needs and market trends. Product managers work closely with cross-functional teams, including engineering, design, marketing, and sales, to bring the product to market and ensure its success. The average salary for a product manager in the United States is around $123,000 per year, with additional compensation in the form of bonuses, profit-sharing, and stock options.");

        productManagerData.add(new String[]{
                "What is the career path for becoming a product manager?",
                "How can I advance in my career as a product manager?",
                "What are the typical educational backgrounds for aspiring product managers?",
                "What skills are essential for a successful product manager?",
                "How important is gaining experience in related roles (e.g., marketing, engineering) for aspiring product managers?",
                "What are some common career trajectories for product managers, such as transitioning to executive roles or starting their own companies?",
                "How does the demand for product managers vary across different industries?",
                "What are the key responsibilities of a product manager?",
                "How can networking and professional connections impact a product manager's career growth?",
                "Are there any certifications or qualifications that can enhance a product manager's resume?",
                "What are the typical salary ranges for product managers at different career stages and in different geographic locations?"
        }); // Questions

        productManagerData.add(new String[]{
                "The career path for becoming a product manager often involves gaining experience in related roles such as business analysis, project management, or product development. Many product managers have backgrounds in business, engineering, or design.",
                "Product managers can advance in their careers by gaining experience in different domains, taking on leadership roles, pursuing advanced education (e.g., MBA), and demonstrating strong leadership and strategic skills.",
                "Typical educational backgrounds for aspiring product managers include degrees in business administration, marketing, computer science, engineering, or related fields. However, relevant experience and skills are often valued more than specific degrees.",
                "Essential skills for a successful product manager include strategic thinking, market research, product development, project management, communication, leadership, and analytical skills.",
                "Gaining experience in related roles such as marketing, engineering, or design can provide valuable insights and skills that are beneficial for aspiring product managers.",
                "Common career trajectories for product managers include progressing from associate product manager to product manager, then to senior product manager or director of product management. Some product managers may also transition to executive roles or start their own companies.",
                "The demand for product managers varies across industries, with technology, e-commerce, finance, and healthcare being some of the sectors with high demand for product management expertise.",
                "Key responsibilities of a product manager include defining product vision and strategy, conducting market research, prioritizing features, collaborating with cross-functional teams, and managing the product lifecycle.",
                "Networking and professional connections can impact a product manager's career growth by providing access to job opportunities, industry insights, mentorship, and potential collaborators.",
                "Certifications or qualifications that can enhance a product manager's resume include Certified Scrum Product Owner (CSPO), Pragmatic Marketing Certification (PMC), or Project Management Professional (PMP) certification.",
                "Salary ranges for product managers vary based on factors such as experience, location, industry, and company size. Entry-level product managers may earn around $70,000 to $100,000 annually, while experienced professionals can earn six-figure salaries or higher."
        }); // Answers

        roadmapData.put("Product Manager", productManagerData);

    }

    public static List<Object> getRoadmapData(String career) {
        return roadmapData.get(career);
    }
}
