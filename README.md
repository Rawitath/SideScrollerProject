 # Lucy Engine by Group นี้ไม่ได้นอน
 ## Repository นี้ถูกเปิดไว้เป็นสาธารณะเพื่อเป็นวิทยาทานให้กับทุกคน
 An Engine for Little Lucy
 คนบ้าอะไรทำ Engine เอง
 ## Basic Lucy Control
 D = ขวา
 A =  ซ้าย
 W,Spacebar = กระโดด
 Q E = เลื่อนช่องเก็บของ
 K = ใช้item
 J = ยิง
 F = interact 

 ## NEW FEATURE!!!
 ### Debug Mode
โหมดที่เมื่อเปิดแล้วจะทำให้เห็น Collider, Boundary ของทุก Entity และมี Debug Window สำหรับดูข้อมูล Entity แต่ละตัวได้<br>
ใช้งานได้โดยการ <b>ไปที่ Main.java แล้ว Uncomment คำสั่งนี้</b>
```java
DebugManager.useDebug();
```

สามารถคลิกที่ Boundary (สี่เหลี่ยมเล็กๆบน Entity) เพื่อให้ข้อมูลขึ้นบน Debug Window ได้<br>
สามารถขยับกล้องได้อิสระ โดย<br>
Arrow Keys ใช้ขยับกล้อง<br>
Scroll Mouse หรือ . กับ / เพื่อซูม<br> 
; กับ ' เพื่อปรับความเร็วการขยับกล้อง<br>
### MouseControlable อัปเกรด
ทำให้ MouseControlable รองรับการเลื่อน Scroll Mouse และ Motion ของ Mouse ได้
### Bug fixes
แก้ระบบการซูมกลอง ไม่ให้ซูมแต่ตรงกลาง
 ## หลักการทำงาน
Lucy Engine จะแบ่งออกเป็น <b>Scene</b> กับ <b>Entity</b> โดย<br>
- Scene เป็นฉากที่ Engine จะโหลดขึ้น เช่น ฉากเมนูเริ่มเกม ฉากปราสาท ฉากป่า
- Entity เหมือนตัวละครหรือสิ่งของในฉาก เช่น ผู้เล่น หิน ศัตรู ปุ่มเริ่มเกม

โดยเราจะเริ่มจากสร้าง Scene แล้ว สร้าง Entity เพิ่ม Entity เข้าไปใน Scene แล้ว loadScene ผ่าน SceneManager

### SceneManager คือตัวโหลด Scene ที่สามารถเรียกใช้จาก class ไหนก็ได้

## World Space
World Space คือพื้นที่เสมือนบน Scene มีค่าเท่าไหร่ก็ได้ Entity จะมีค่าตำแหน่งบน World Space เพื่อใช้ทำระบบและแแสดงผล
## Vector2
คือ class ที่ไว้เก็บค่า float เป็นคู่ มี X กับ Y และมี method สำหรับบวกและคูณอยู่ในนั้น\
มีอีกแบบคือ Vector2Int ที่เหมือน Vector2 แต่เก็บเป็น int แทน
 ## การสร้างเบื้องต้น
 ### วิธีสร้าง Scene
 1. สร้าง class ของเราใน package Main.Scenes
 2. extend Scene ใน class ของเรา
 3. เพิ่ม scene ของเรา ใน Main.java ใน package Main โดยใช้คำสั่งนี้<br>
 ```java
SceneManager.addScene(new sceneของเรา());
```
ในตอนเริ่มรันโปรเจ็ค Scene ที่ถูกเพิ่มก่อนจะเป็น Scene แรกที่ถูกโหลด
 ### วิธีสร้าง Entity (สิ่งของ / ตัวละครในฉาก)
 1. สร้าง class ของเราใน package Main.Entities
 2. extend Entity หรือ class ใน Entities.Templates ใน class ของเรา
 3. เพิ่ม class ของเราใน Scene ที่เราต้องการใน Main.Scenes ใน load() โดยใช้คำสั่ง
```java
addEntity(new entityของเรา(this));
```
## ส่วนประกอบหลักๆใน Entity
เมื่อสร้าง Entity ขึ้นมาจะมี Method ที่ต้อง Implement อยู่ดังนี้ การโปรแกรมตัว Entity จะใส่ใน Method นี้
### Constructor
เรียกใช้เมื่อ Object ถูกสร้าง
### Start
เรียกใช้ครั้งเดียวเมื่อทุกอย่างใน Scene โหลดเสร็จ
### Update
เรียกใช้ซ้ำไปเรื่อยๆ **ให้เร็วที่สุดเท่าที่จะเป็นไปได้**
### FixedUpdate
เรียกใช้ซ้ำไปเรื่อยๆ **ตาม updateSpeed ของ MainEngine**
## กล้อง
กล้อง เป็น Entity ชนิดหนึ่ง เป็นตัวไว้กำหนดว่า Entity ที่สามารถแสดงผลได้ (เช่น SpriteEntity) จะอยู่ตรงไหนบนหน้าจอ โดยกล้องนั้นจะมีติดอยู่กับ Scene โดย 1 Scene จะมีกล้อง 2 แบบคือ
1. Camera เป็นกล้องสำหรับ Render Entity ปกติที่อยู่บน World Space สามารถขยับตัวกล้องและซูมได้
2. UIView เป็นกล้องสำหรับ Render เฉพาะ UI เท่านั้น โดยจะ Render ตำแหน่งตามค่า ReferenceResolution
## การเปลี่ยนตำแหน่งและขนาด
ใช้กับ class ที่ extends จาก Entity
ตำแหน่งจะมี
```java
setPosition(ค่าตำแหน่ง Vector2);
```
และ
```java
setLocalPosition(ค่าตำแหน่ง Vector2);
```
ขนาดจะมี
```java
setScale(ค่าขนาด Vector2);
```
และ
```java
setLocalScale(ค่าขนาด Vector2);
```
โดย Local คืออิงตำแหน่งจาก Parent ส่วนถ้าไม่มีคำนำหน้าจะเป็นตำแหน่งบน World Space
## การเปลี่ยนรูป Sprite
ใช้กับ class ที่ extends จาก SpriteEntity
ใช้คำสั่ง
```java
setImage(ที่อยู่รูป เช่น ถ้าอยู่ในโฟลเดอร์ res ในโปรเจ็ค ก็จะเป็น "res/รูป.jpg");
```
## การตรวจจับการชน
ใช้กับ class ที่ extends จาก CollidableEntity หรือ implements Collidables
โดยจะมี Method 
```java
public void onColliderEnter(Collider other)
public void onColliderStay(Collider other)
public void onColliderExit(Collider other)
```
Enter จะรันเมื่อมี Collider เข้ามาชนเรา
Stay จะรันไปเรื่อยๆเมื่อยังมี Collider ชนเรา
Exit จะรันเมื่อมี Collider ออกจากเรา
## การเรียกใช้กล้อง
ใช้กับ class ที่ extends จาก Entity\
ใช้คำสั่ง
```java
getScene().getCamera();
```
หรือ
```java
getScene().getUIView();
```
สำหรับ UIView
## UIEntity
UIEntity เป็น Entity ที่ extends มาจาก Entity แตกต่างกันที่ UIEntity จะถูก Render โดย UIView และ การ setPosition และ setScale จะใช้ขนาดตาม ReferenceResolution ของ UIView
มีคลาสที่ extends จาก UIEntity เช่น UIButton, UIText, UISlider
## การเปลี่ยนตำแหน่งและขนาดของ UI
จะคล้ายกับ Entity แต่แทนที่จะอิงกับ WorldSpace จะไปอิงกับ ReferenceResolution ใน UIView แทน
## ReferenceResolution
เป็นค่าขนาดหน้าจอจำลองใน UIView โดยค่าเริ่มต้นคือ (1920, 1080)
โดยที่จุด (0, 0) จะอยู่ตรงกลาง
## ScreenAnchor
เป็นการกำหนดจุด (0, 0) ให้กับ UIEntity บน UIView โดยจะมีค่า static final ใน UIEntity เช่น
```java
setScreenAnchor(TOP_LEFT);
```
เป็นการ set ให้ UIEntity นี้ เมื่อ setPosition(new Vector2(0, 0)) UIEntity นี้จะอยู่ที่มุมซ้ายบนของจอ\
หาก UIEntity เป็นลูกของ UIEntity อื่น ScreenAnchor จะอิงตามตำแหน่งในขนาดของ UIEntity แม่
## อื่นๆ
มีระบบอื่นๆเช่น AudioSource และ Animator ที่ยังไม่ได้เขียน แต่มีตัวอย่างอยู่ใน ExampleScene และ package ที่มี .Example อยู่ด้านหลัง\
ปล. loop กับ play AudioSource อยู่ใน Lucy.java
## Attributes ในคลาสหลักๆ
### Entity
1. <b>Position</b> คือค่าตำแหน่งบน World Space ค่าเริ่มต้นคือ <b>(0, 0)</b>
2. <b>Scale</b> คือค่าขนาดบน World Space ค่าเริ่มต้นคือ <b>(1, 1)</b>
3. <b>Name</b> คือชื่อของ Entity ค่าเริ่มต้นคือ <b>ชื่อ package.ชื่อ class</b>
4. <b>Tag</b> คือชื่อประเภท ส่วนมากใช้กับ Collider ค่าเริ่มต้นคือ <b>Default</b>
5. <b>Childs</b> คือ Entity ที่เป็นลูกของ Entity นี้ มีได้หลายตัว ค่าเริ่มต้นคือ <b>ArrayList ว่าง</b>
6. <b>Parent</b> คือ Entity แม่ของ Entity นี้ ค่าเริ่มต้นคือ <b>null</b>
7. <b>LocalPosition</b> คือค่าตำแหน่งบน Parent Entity หาก Entity นี้ไม่ได้เป็นลูกของ Entity จะมีค่าเท่ากับ Position ปกติ ค่าเริ่มต้นคือ <b>(0, 0)</b>
8. <b>LocalScale</b> คือค่าขนาดบน Parent Entity หาก Entity นี้ไม่ได้เป็นลูกของ Entity จะมีค่าเท่ากับ Scale ปกติ  ค่าเริ่มต้นคือ <b>(1, 1)</b>
9. <b>BoundaryVisible</b> คือการทำให้ กรอบขนาดของ Entity มองเห็นได้หรือไม่ ค่าเริ่มต้นคือ <b>false</b>
9. <b>Scene</b> คือ Scene ที่ Entity นี้อยู่ ค่าเริ่มต้นคือ <b>Parameter ของ Constructor</b>
### SpriteEntity
คลาสนี้ extends มาจาก <b>Entity</b> และมี Attributes เพิ่มดังนี้
1. <b>Sprite</b> คือรูปของ Entity นี้ ค่าเริ่มต้นคือ <b>null</b>
2. <b>SpriteSize</b> คือขนาดของรูปเป็น pixel จะปรับไปตาม Sprite แต่สามารถ set เองได้ ค่าเริ่มต้นคือ <b>(100, 100)</b>
3. <b>PixelRatio</b> คือค่าอัตราส่วนระหว่างหน่วย World Space ต่อ 1 pixel ของ sprite ค่าเริ่มต้นคือ <b>0.01</b>
4. <b>Anchor</b> คือค่าที่กำหนดให้จุด (0, 0) อยู่ที่ตำแหน่งไหนของ Entity ค่าเริ่มต้นคือ <b>(0, 0)</b>
5. <b>SpriteVisible</b> คือการทำให้ sprite มองเห็นได้หรือไม่ ค่าเริ่มต้นคือ <b>true</b>
6. <b>Flip</b> คือทิศทางการพลิกรูป หากเป็น (0, 0) จะไม่มีการพลิก ค่าเริ่มต้นคือ <b>(0, 0)</b>
### CollidableEntity
คลาสนี้ extends มาจาก <b>SpriteEntity</b> implements จาก <b>Collidable</b> และมี Attributes เพิ่มดังนี้
1. <b>Collider</b> คือกรอบที่ไว้ตรวจจับการชนของ Entity ค่าเริ่มต้นคือ <b>new Collider(Entity e)</b>
2. <b>ColliderVisible</b> คือการทำให้ collider มองเห็นได้หรือไม่ ค่าเริ่มต้นคือ <b>false</b>
### Collider
คลาสนี้เป็นคลาสที่ประกอบอยู่ใน CollidableEntity มีไว้เพื่อตรวจจับการชน มี Attributes ดังนี้
1. <b>Entity</b> คือค่าที่เก็บ Entity ที่ถูก Collider นี้ประกอบอยู่ ค่าเริ่มต้นได้จาก <b>Parameter ของ Constructor</b>
2. <b>Center</b> คือ ค่าจุดกึ่งกลางของ Collider นี้บน Entity ค่าเริ่มต้นได้จาก<b>Parameter ของ Constructor</b>
3. <b>Bound</b> ค่า-okfของ Collider นี้บน Entity  ค่าเริ่มต้นได้จาก <b>Parameter ของ Constructor</b>
4. <b>CollidedObjects</b> คือจำนวน Collider ที่ชนกับ Collider นี้อยู่ ค่าเริ่มต้นคือ <b>new Arraylist<>()</b>
### UIEntity
ช่วยเขียนให้หน่อย 555555
